package com.muramsyah.mygithubusers.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val url: String,
    val followersUrl: String,
    val followingUrl: String,
    val isFavorite: Boolean = false
) : Parcelable
