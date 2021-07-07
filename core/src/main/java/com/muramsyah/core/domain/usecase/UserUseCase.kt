package com.muramsyah.core.domain.usecase

import com.muramsyah.core.data.Resource
import com.muramsyah.core.domain.model.DetailUser
import com.muramsyah.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getDetailUser(username: String): Flow<Resource<DetailUser>>
    fun setFavoriteUser(detailUser: DetailUser, newState: Boolean)
    fun getFavoriteUsers(): Flow<List<User>>
    suspend fun getSearchUser(username: String): List<User>
}