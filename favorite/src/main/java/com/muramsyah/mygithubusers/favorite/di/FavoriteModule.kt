package com.muramsyah.mygithubusers.favorite.di

import com.muramsyah.mygithubusers.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}