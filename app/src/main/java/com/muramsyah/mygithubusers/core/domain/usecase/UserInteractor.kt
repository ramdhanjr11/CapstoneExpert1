package com.muramsyah.mygithubusers.core.domain.usecase

import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.domain.model.User
import com.muramsyah.mygithubusers.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository): UserUseCase {

    override fun getAllUser(): Flow<Resource<List<User>>> = userRepository.getAllUser()

}