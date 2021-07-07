package com.muramsyah.mygithubusers.di

import com.muramsyah.mygithubusers.core.domain.usecase.UserInteractor
import com.muramsyah.mygithubusers.core.domain.usecase.UserUseCase
import com.muramsyah.mygithubusers.detail.DetailViewModel
import com.muramsyah.mygithubusers.favorite.FavoriteViewModel
import com.muramsyah.mygithubusers.home.HomeViewModel
import com.muramsyah.mygithubusers.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}