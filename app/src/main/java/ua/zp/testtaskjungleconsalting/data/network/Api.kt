package ua.zp.testtaskjungleconsalting.data.network

import retrofit2.http.GET
import ua.zp.testtaskjungleconsalting.data.network.responses.UsersListResponse

interface Api {
    @GET("/users")
    suspend fun getListUsers(): List<UsersListResponse>
}