package com.example.made_submission1.core.domain.usecase

import com.example.made_submission1.core.domain.model.Movies
import com.example.made_submission1.core.domain.repository.IMovieRepository
import com.example.made_submission1.core.source.Resource
import kotlinx.coroutines.flow.Flow

class MovieInteractor constructor(private val movieRespository: IMovieRepository) : MovieUseCase{
    override fun getAllMovie(): Flow<Resource<List<Movies>>> = movieRespository.getAllMovie()

    override fun getFavoriteMovie(): Flow<List<Movies>> = movieRespository.getFavoriteMovie()

    override fun setFavoriteMovie(movies: Movies, state: Boolean) = movieRespository.setFavoriteMovie(movies, state)
}