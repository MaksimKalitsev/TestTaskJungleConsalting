package ua.zp.testtaskjungleconsalting.data.models

import ua.zp.testtaskjungleconsalting.data.db.RepoEntity

data class RepoItem(
    val id: Long,
    val name: String,
    val html_url: String
){
    fun toRepoEntity(): RepoEntity =
        RepoEntity(
            id = id,
            name = name,
            htmlUrl = html_url,
        )
}
