package ua.zp.testtaskjungleconsalting.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.zp.testtaskjungleconsalting.data.models.Users

@Parcelize
data class UsersListResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String
) : Parcelable {
    fun toUsers(): Users =
        Users(
            login = login,
            id = id,
            avatar = avatarUrl,
            repos_url = reposUrl
        )
}
