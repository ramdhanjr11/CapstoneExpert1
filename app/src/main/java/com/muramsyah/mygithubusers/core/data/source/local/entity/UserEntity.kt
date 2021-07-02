package com.muramsyah.mygithubusers.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val login: String,
    @PrimaryKey
    @NonNull
    val id: Int,
    val avatarUrl: String,
    val url: String,
    val followersUrl: String,
    val followingUrl: String,
    val isFavorite: Boolean = false
)
