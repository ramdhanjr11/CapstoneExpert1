package com.muramsyah.core.data.source.remote

import android.util.Log
import com.muramsyah.core.data.source.remote.network.ApiResponse
import com.muramsyah.core.data.source.remote.network.ApiService
import com.muramsyah.core.data.source.remote.response.DetailUserResponse
import com.muramsyah.core.data.source.remote.response.ListUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    // Github Token
    private val token = "cd64c17d1ed4aa9d1a2bd48793e9a9ca0e025a8d"

    suspend fun getAllListUser(): Flow<ApiResponse<List<ListUserResponse>>> {
        // shuffle the numbers
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        // get data from api
        return flow {
            try {
                val response = apiService.getListUsers(numbers.random().toString(), token)
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

    suspend fun getDetailUser(username: String): Flow<ApiResponse<DetailUserResponse>> {
        // get detail user data from api
        return flow {
            try {
                val response = apiService.getDetailUser(username)
                Log.d("RemoteDataSource", response.name.toString())
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchUser(username: String): Flow<ApiResponse<List<ListUserResponse>>> {

        // get data user from search api
        return flow {
            try {
                val response = apiService.getSearchUser(username, token).items
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