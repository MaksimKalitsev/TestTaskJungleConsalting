package ua.zp.testtaskjungleconsalting.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import retrofit2.HttpException
import ua.zp.testtaskjungleconsalting.data.db.UserEntity
import ua.zp.testtaskjungleconsalting.repository.IUsersRepository
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val usersRepository: IUsersRepository
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val usersResult = usersRepository.getUsers(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            usersResult.getOrNull()?.let { users ->
                val updateResult = usersRepository.updateUsersTable(users)
                updateResult.getOrNull()?.let {
                    return MediatorResult.Success(
                        endOfPaginationReached = users.isEmpty()
                    )
                }
            } ?: return MediatorResult.Error(
                usersResult.exceptionOrNull() ?: Exception("Unknown Exception")
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }
}
