package com.muramsyah.mygithubusers.core.domain.repository

import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getAllUser(): Flow<Resource<List<User>>>

}