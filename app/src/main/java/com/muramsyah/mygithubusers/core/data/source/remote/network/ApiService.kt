package com.muramsyah.mygithubusers.core.data.source.remote.network

import com.muramsyah.mygithubusers.core.data.source.remote.response.DetailUserResponse
import com.muramsyah.mygithubusers.core.data.source.remote.response.ListUserResponse
import com.muramsyah.mygithubusers.core.data.source.remote.response.SearchResponse
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getListUsers(
        @Query("since") since: String,
        @Header("Authorization") token: String
    ): List<ListUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token cd64c17d1ed4aa9d1a2bd48793e9a9ca0e025a8d")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("search/users")
    @Headers("Authorization: token cd64c17d1ed4aa9d1a2bd48793e9a9ca0e025a8d")
    suspend fun getSearchUser(
        @Query("q") username: String
    ): SearchResponse
}