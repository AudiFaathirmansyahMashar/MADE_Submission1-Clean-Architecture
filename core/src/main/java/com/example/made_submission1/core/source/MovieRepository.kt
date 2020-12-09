package com.example.made_submission1.core.source

import com.example.made_submission1.core.domain.model.Movies
import com.example.made_submission1.core.domain.repository.IMovieRepository
import com.example.made_submission1.core.source.local.LocalDataSource
import com.example.made_submission1.core.source.remote.RemoteDataSource
import com.example.made_submission1.core.source.remote.network.ApiResponse
import com.example.made_submission1.core.source.remote.response.Result
import com.example.made_submission1.core.utils.AppExecutors
import com.example.made_submission1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<Result>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<Result>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<Result>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movies>> =
        localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movies: Movies, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute{ localDataSource.setFavoriteMovie(movieEntity, state)}
    }
}