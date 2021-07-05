package com.muramsyah.mygithubusers.core.domain.usecase

import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getDetailUser(username: String): Flow<Resource<DetailUser>>
    fun setFavoriteUser(user: User, newState: Boolean)
}