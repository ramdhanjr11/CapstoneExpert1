package com.muramsyah.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String
)