package com.muramsyah.mygithubusers.core.domain.repository

import com.muramsyah.mygithubusers.core.data.Resource
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getDetailUser(username: String): Flow<Resource<DetailUser>>
    fun setFavoriteUser(detailUser: DetailUser, newState: Boolean)
    fun getFavoriteUsers(): Flow<List<User>>
    suspend fun getSearchUser(username: String): List<User>
}