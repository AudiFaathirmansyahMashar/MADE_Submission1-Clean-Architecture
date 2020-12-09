package com.example.made_submission1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made_submission1.core.domain.usecase.MovieUseCase

class FavoritesViewModel(movieUseCase: MovieUseCase) : ViewModel(){
    val favorite = movieUseCase.getFavoriteMovie().asLiveData()
}