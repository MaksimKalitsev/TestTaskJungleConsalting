package ua.zp.testtaskjungleconsalting.repository

import ua.zp.testtaskjungleconsalting.data.models.RepoItem
import ua.zp.testtaskjungleconsalting.data.models.User
import ua.zp.testtaskjungleconsalting.data.network.Api
import javax.inject.Inject


interface IUsersRepository {
    suspend fun getUsers(page: Int, pageCount: Int): Result<List<User>>

    suspend fun updateUsersTable(users: List<User>): Result<Unit>
}
class UsersRepository @Inject constructor(private val api: Api): IUsersRepository {
    override suspend fun getUsers(page: Int, pageCount: Int): Result<List<User>> = try {
        val users = api.getListUsers(page, pageCount).map { it.toUser() }
        Result.success(users)
    } catch (ex: Exception) {
        Result.failure(ex)
    }
    override suspend fun updateUsersTable(users: List<User>): Result<Unit> = try {
        TODO("Not yet implemented")
    } catch (ex: Exception) {
        Result.failure(ex)
    }


}