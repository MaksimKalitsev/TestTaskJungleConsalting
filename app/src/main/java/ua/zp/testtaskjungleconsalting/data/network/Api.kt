package ua.zp.testtaskjungleconsalting.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ua.zp.testtaskjungleconsalting.data.network.responses.UserReposResponse
import ua.zp.testtaskjungleconsalting.data.network.responses.UserResponse

interface Api {
    @GET("/users")
    suspend fun getListUsers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<UserResponse>

    @GET("/users/{user}/repos")
    suspend fun getReposUser(
        @Path("user") user: String,
        @Query("page") page: Long,
        @Query("per_page") pageCount: Int
    ): List<UserReposResponse>

}