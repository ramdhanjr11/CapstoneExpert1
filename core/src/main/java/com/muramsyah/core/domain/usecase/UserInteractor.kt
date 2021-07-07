package com.muramsyah.core.domain.usecase

import com.muramsyah.core.data.Resource
import com.muramsyah.core.domain.model.DetailUser
import com.muramsyah.core.domain.model.User
import com.muramsyah.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository): UserUseCase {
    override fun getAllUser(): Flow<Resource<List<User>>> = userRepository.getAllUser()
    override fun getDetailUser(username: String): Flow<Resource<DetailUser>> = userRepository.getDetailUser(username)
    override fun setFavoriteUser(detailUser: DetailUser, newState: Boolean) = userRepository.setFavoriteUser(detailUser, newState)
    override fun getFavoriteUsers(): Flow<List<User>> = userRepository.getFavoriteUsers()
    override suspend fun getSearchUser(username: String): List<User> = userRepository.getSearchUser(username)
}