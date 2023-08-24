package ua.zp.testtaskjungleconsalting.repository

import ua.zp.testtaskjungleconsalting.data.models.RepoItem
import ua.zp.testtaskjungleconsalting.data.network.Api
import javax.inject.Inject

interface IReposRepository {

    suspend fun getRepos(login: String, page: Long, pageCount: Int): Result<List<RepoItem>>
}

class ReposRepository @Inject constructor(private val api: Api) : IReposRepository {

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
}