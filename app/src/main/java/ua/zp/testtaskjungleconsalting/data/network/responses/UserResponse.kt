package ua.zp.testtaskjungleconsalting.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.zp.testtaskjungleconsalting.data.db.UserEntity
import ua.zp.testtaskjungleconsalting.data.models.User

@Parcelize
data class UserResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String
) : Parcelable {

    fun toUser(): User =
        User(
            login = login,
            id = id,
            avatar = avatarUrl,
            repos_url = reposUrl
        )
}
