package com.muramsyah.mygithubusers.core.data.source.remote.network

import com.muramsyah.mygithubusers.core.data.source.remote.response.ListUserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getListUsers(
        @Query("since") since: String,
        @Header("Authorization") token: String
    ): List<ListUserResponse>
}