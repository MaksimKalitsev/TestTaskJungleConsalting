package ua.zp.testtaskjungleconsalting.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.zp.testtaskjungleconsalting.data.models.RepositoryItem

@Parcelize
data class UserReposResponse(
    val name: String,
    @SerializedName("html_url")
    val htmlUrl: String,
) : Parcelable {
    fun toRepos():RepositoryItem =
        RepositoryItem(
            name = name,
            html_url = htmlUrl
        )
}


