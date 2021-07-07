package com.muramsyah.mygithubusers.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muramsyah.core.domain.usecase.UserUseCase

class HomeViewModel(userUseCase: UserUseCase) : ViewModel() {
    val getAllUser = userUseCase.getAllUser().asLiveData()
}