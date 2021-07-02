package com.muramsyah.mygithubusers.core.data.source.local.room

import androidx.room.Database
import com.muramsyah.mygithubusers.core.data.source.local.entity.ListUserEntity

@Database(entities = [ListUserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase {
    abstract fun userDao(): UserDao
}