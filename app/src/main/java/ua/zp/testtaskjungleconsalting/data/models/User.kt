package ua.zp.testtaskjungleconsalting.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.zp.testtaskjungleconsalting.data.db.UserEntity

@Parcelize
data class User(
    val login: String,
    val id: Int,
    val avatar: String,
    val repos_url: String
): Parcelable {
    fun toUserEntity(): UserEntity =
        UserEntity(
            id  = id,
            login = login,
            avatarUrl = avatar,
            reposUrl = repos_url
        )
}
