package ua.zp.testtaskjungleconsalting.repository

import ua.zp.testtaskjungleconsalting.data.network.Api
import ua.zp.testtaskjungleconsalting.data.network.responses.UserReposResponse
import ua.zp.testtaskjungleconsalting.data.network.responses.UsersListResponse
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: Api) {
    suspend fun getUsers(): List<UsersListResponse> = api.getListUsers()
    suspend fun getRepos(login: String): List<UserReposResponse> = api.getReposUser(login)
}