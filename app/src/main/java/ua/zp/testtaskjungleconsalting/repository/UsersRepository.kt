package ua.zp.testtaskjungleconsalting.repository

import ua.zp.testtaskjungleconsalting.data.network.Api
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: Api) {
    suspend fun getUsers() = api.getListUsers()
}