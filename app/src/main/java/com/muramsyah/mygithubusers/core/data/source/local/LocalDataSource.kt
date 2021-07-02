package com.muramsyah.mygithubusers.core.data.source.local

import com.muramsyah.mygithubusers.core.data.source.local.entity.UserEntity
import com.muramsyah.mygithubusers.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<UserEntity>> = userDao.getAllUser()

    suspend fun insertUsers(users: List<UserEntity>) = userDao.insertUsers(users)

}