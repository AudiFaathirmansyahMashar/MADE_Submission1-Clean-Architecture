package com.example.made_submission1.core.domain.usecase

import com.example.made_submission1.core.domain.model.Movies
import com.example.made_submission1.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovie(): Flow<List<Movies>>
    fun setFavoriteMovie(movies: Movies, state: Boolean)
}