package com.muramsyah.mygithubusers.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muramsyah.mygithubusers.core.domain.usecase.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase): ViewModel() {
    val favoriteUsers = userUseCase.getFavoriteUsers().asLiveData()
}