package com.muramsyah.mygithubusers.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muramsyah.core.domain.usecase.UserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class SearchViewModel(userUseCase: UserUseCase): ViewModel() {
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            userUseCase.getSearchUser(it)
        }
        .asLiveData()
}