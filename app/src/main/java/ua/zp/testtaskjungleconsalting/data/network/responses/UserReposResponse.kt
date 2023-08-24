package ua.zp.testtaskjungleconsalting.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.zp.testtaskjungleconsalting.data.models.RepoItem

@Parcelize
data class UserReposResponse(
    val id: Long,
    val name: String,
    @SerializedName("html_url")
    val htmlUrl: String,
) : Parcelable {
    fun toRepositoryItem(): RepoItem =
        RepoItem(
            id = id,
            name = name,
            html_url = htmlUrl
        )
}


