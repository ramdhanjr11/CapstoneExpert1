package com.muramsyah.mygithubusers.core.domain.model

data class DetailUser(
    val login: String? = null,
    val id: Int? = null,
    val avatarUrl: String? = null,
    val url: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val bio: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val publicRepos: Int? = null,
    val name: String? = null,
    val location: String? = null,
    val followersUrl: String? = null,
    val followingUrl: String? = null,
)
