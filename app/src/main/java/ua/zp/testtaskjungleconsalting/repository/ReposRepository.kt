package ua.zp.testtaskjungleconsalting.repository

import androidx.room.withTransaction
import ua.zp.testtaskjungleconsalting.data.db.Database
import ua.zp.testtaskjungleconsalting.data.models.RepoItem
import ua.zp.testtaskjungleconsalting.data.network.Api
import javax.inject.Inject

interface IReposRepository {
    suspend fun getRepos(login: String, page: Long, pageCount: Int): Result<List<RepoItem>>
    suspend fun updateReposTable(repos: List<RepoItem>): Result<Unit>
}

class ReposRepository @Inject constructor(private val api: Api, private val reposDb: Database) :
    IReposRepository {
    override suspend fun getRepos(
        login: String,
        page: Long,
        pageCount: Int
    ): Result<List<RepoItem>> = try {
        val repos = api.getReposUser(login, page, pageCount).map { it.toRepositoryItem() }
        Result.success(repos)
    } catch (ex: Exception) {
        Result.failure(ex)
    }

    override suspend fun updateReposTable(repos: List<RepoItem>): Result<Unit> = try{
        reposDb.withTransaction {
            reposDb.daoRepo.clearAll()
            val repoEntities = repos.map { it.toRepoEntity() }
            reposDb.daoRepo.upsertAll(repoEntities)
        }
        Result.success(Unit)
    } catch (ex: Exception) {
        Result.failure(ex)
    }

}