package com.example.made_submission1.core.source.remote.network

import com.example.made_submission1.core.BuildConfig
import com.example.made_submission1.core.source.remote.response.Movie
import retrofit2.http.GET

interface ApiService {
    @GET(BuildConfig.MOVIE)
    suspend fun getMovies(): Movie
}