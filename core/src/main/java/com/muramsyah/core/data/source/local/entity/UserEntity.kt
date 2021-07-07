package com.muramsyah.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    var login: String,
    @PrimaryKey
    @NonNull
    var id: Int,
    var avatarUrl: String,
    var url: String,
    var followersUrl: String,
    var followingUrl: String,
    var isFavorite: Boolean = false
)
