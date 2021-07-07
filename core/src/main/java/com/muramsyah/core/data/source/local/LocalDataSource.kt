package com.muramsyah.core.data.source.local

import com.muramsyah.core.data.source.local.entity.UserEntity
import com.muramsyah.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<UserEntity>> = userDao.getAllUser()

    suspend fun insertUsers(users: List<UserEntity>) = userDao.insertUsers(users)

    fun setFavoriteUser(user: UserEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateFavoriteUser(user)
    }

    fun getFavoriteUsers(): Flow<List<UserEntity>> = userDao.getFavoriteUsers()
}