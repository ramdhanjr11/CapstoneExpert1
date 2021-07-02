package com.muramsyah.mygithubusers.core.data.source.local

import com.muramsyah.mygithubusers.core.data.source.local.entity.ListUserEntity
import com.muramsyah.mygithubusers.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<ListUserEntity>> = userDao.getAllUser()

}