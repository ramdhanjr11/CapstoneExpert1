package com.muramsyah.mygithubusers.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
import com.muramsyah.mygithubusers.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel(){
    fun getDetailUser(username: String): LiveData<com.muramsyah.mygithubusers.core.data.Resource<DetailUser>> {
        return userUseCase.getDetailUser(username).asLiveData()
    }

    fun setFavoriteUser(detailUser: DetailUser, newState: Boolean) = userUseCase.setFavoriteUser(detailUser, newState)
}