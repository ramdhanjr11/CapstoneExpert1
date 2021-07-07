package com.muramsyah.core.data.source.remote.network

import com.muramsyah.core.data.source.remote.response.DetailUserResponse
import com.muramsyah.core.data.source.remote.response.ListUserResponse
import com.muramsyah.core.data.source.remote.response.SearchResponse
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getListUsers(
        @Query("since") since: String,
        @Header("Authorization") token: String
    ): List<ListUserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") username: String,
        @Header("Authorization") token: String
    ): SearchResponse
}