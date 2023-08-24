package ua.zp.testtaskjungleconsalting.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ua.zp.testtaskjungleconsalting.data.db.RepoEntity
import ua.zp.testtaskjungleconsalting.data.db.UserDatabase
import ua.zp.testtaskjungleconsalting.repository.IReposRepository

@OptIn(ExperimentalPagingApi::class)
class ReposRemoteMediator(
    private val repoDb: UserDatabase, // todo: get it out of here
    private val reposRepository: IReposRepository
): RemoteMediator<Int, RepoEntity>() {

    var login: String? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoEntity>
    ): MediatorResult {
        return try {


            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {

                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val loginSafe = login ?: throw IllegalStateException("Login isn't available")

            val reposResult = reposRepository.getRepos(
                login = loginSafe,
                page = loadKey,
                pageCount = state.config.pageSize
            )

            reposResult.getOrNull()?.let {  repos ->
                repoDb.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        repoDb.daoRepo.clearAll()
                    }
                    val repoEntities = repos.map { it.toRepoEntity() }
                    repoDb.daoRepo.upsertAll(repoEntities)
                }

                return MediatorResult.Success(
                    endOfPaginationReached = repos.isEmpty()
                )

            } ?: return MediatorResult.Error(reposResult.exceptionOrNull() ?: Exception("Unknown Exception"))
        } catch(e: Exception) {
            MediatorResult.Error(e)
        }

    }
}