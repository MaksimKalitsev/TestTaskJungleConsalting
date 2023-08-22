package ua.zp.testtaskjungleconsalting.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val login: String,
    val id: Int,
    val avatar: String,
    val repos_url: String
):Parcelable
