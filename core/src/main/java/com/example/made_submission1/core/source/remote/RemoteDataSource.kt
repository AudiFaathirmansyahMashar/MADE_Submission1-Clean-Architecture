package com.example.made_submission1.core.source.remote

import com.example.made_submission1.core.source.remote.network.ApiResponse
import com.example.made_submission1.core.source.remote.network.ApiService
import com.example.made_submission1.core.source.remote.response.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService){

    fun getAllMovies(): Flow<ApiResponse<List<Result>>> {

        return flow {
            try{
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                }else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}