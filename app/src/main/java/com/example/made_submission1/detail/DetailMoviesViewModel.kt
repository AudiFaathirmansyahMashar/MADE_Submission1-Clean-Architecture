package com.example.made_submission1.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made_submission1.core.domain.model.Movies
import com.example.made_submission1.core.domain.usecase.MovieUseCase
import com.example.made_submission1.core.source.remote.response.Movie

class DetailMoviesViewModel(private val movieUseCase: MovieUseCase) : ViewModel(){
    fun setFavoriteTourism(movies: Movies, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movies, newStatus)
}