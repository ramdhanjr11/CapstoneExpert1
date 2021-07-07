package com.muramsyah.mygithubusers.core.domain.usecase

import com.muramsyah.mygithubusers.core.data.Resource
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.model.User
import com.muramsyah.mygithubusers.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository): UserUseCase {
    override fun getAllUser(): Flow<Resource<List<User>>> = userRepository.getAllUser()
    override fun getDetailUser(username: String): Flow<Resource<DetailUser>> = userRepository.getDetailUser(username)
    override fun setFavoriteUser(detailUser: DetailUser, newState: Boolean) = userRepository.setFavoriteUser(detailUser, newState)
    override fun getFavoriteUsers(): Flow<List<User>> = userRepository.getFavoriteUsers()
}