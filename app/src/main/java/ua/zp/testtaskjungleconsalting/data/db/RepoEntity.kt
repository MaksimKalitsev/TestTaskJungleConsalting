package ua.zp.testtaskjungleconsalting.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ua.zp.testtaskjungleconsalting.data.models.RepoItem

@Entity(tableName = "repo_entity",
    indices = [
        Index("id", unique = true)
    ])
data class RepoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val htmlUrl: String,
){
    fun toRepositoryItem(): RepoItem =
        RepoItem(
            id = id,
            name = name,
            html_url = htmlUrl,
        )
}
