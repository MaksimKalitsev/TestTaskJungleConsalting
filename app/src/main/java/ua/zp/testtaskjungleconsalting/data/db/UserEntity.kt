package ua.zp.testtaskjungleconsalting.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ua.zp.testtaskjungleconsalting.data.models.User


@Entity(tableName = "user_entity",
    indices = [
        Index("id", unique = true)
    ])
data class UserEntity (
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
    ){
    fun toUser(): User =
        User(
            id = id,
            login = login,
            avatar = avatarUrl,
            repos_url = reposUrl
        )
}