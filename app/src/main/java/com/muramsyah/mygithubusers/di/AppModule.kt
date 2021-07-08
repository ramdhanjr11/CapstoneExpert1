package com.muramsyah.mygithubusers.di

import com.muramsyah.core.domain.usecase.UserInteractor
import com.muramsyah.core.domain.usecase.UserUseCase
import com.muramsyah.mygithubusers.detail.DetailViewModel
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
    viewModel { SearchViewModel(get()) }
}