package com.example.made_submission1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made_submission1.core.domain.model.Movies
import com.example.made_submission1.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel(){
    val movie = movieUseCase.getAllMovie().asLiveData()

    fun setFavoriteMovie(movies: Movies, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movies, newStatus)
}