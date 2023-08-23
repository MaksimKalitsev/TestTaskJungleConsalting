package ua.zp.testtaskjungleconsalting.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ua.zp.testtaskjungleconsalting.data.network.responses.UserReposResponse
import ua.zp.testtaskjungleconsalting.data.network.responses.UsersListResponse

interface Api {
    @GET("/users")
    suspend fun getListUsers(): List<UsersListResponse>

    @GET("/users/{user}/repos")
    suspend fun getReposUser(
        @Path("user") user: String
    ): List<UserReposResponse>

}