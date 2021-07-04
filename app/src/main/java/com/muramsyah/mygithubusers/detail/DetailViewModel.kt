package com.muramsyah.mygithubusers.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muramsyah.mygithubusers.core.data.source.Resource
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel(){
    fun getDetailUser(username: String): LiveData<Resource<DetailUser>> {
        return userUseCase.getDetailUser(username).asLiveData()
    }
}