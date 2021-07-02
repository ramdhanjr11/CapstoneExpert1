package com.muramsyah.mygithubusers.core.data.source.remote

import android.util.Log
import com.muramsyah.mygithubusers.core.data.source.remote.network.ApiResponse
import com.muramsyah.mygithubusers.core.data.source.remote.network.ApiService
import com.muramsyah.mygithubusers.core.data.source.remote.response.ListUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllListUser(): Flow<ApiResponse<List<ListUserResponse>>> {
        // shuffle the numbers
        val numbers = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val randomNumber = numbers.random()

        // get data from api
        return flow {
            try {
                val response = apiService.getListUsers(randomNumber.toString())
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}