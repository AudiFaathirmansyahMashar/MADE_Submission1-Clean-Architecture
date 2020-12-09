package com.example.made_submission1.di

import com.example.made_submission1.core.domain.usecase.MovieInteractor
import com.example.made_submission1.core.domain.usecase.MovieUseCase
import com.example.made_submission1.detail.DetailMoviesViewModel
import com.example.made_submission1.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
}