package ua.zp.testtaskjungleconsalting.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersListResponse(
    val login: String,
    val id: Int,
    val avatar: String,
    @SerializedName("repos_url")
    val reposUrl: String
): Parcelable
