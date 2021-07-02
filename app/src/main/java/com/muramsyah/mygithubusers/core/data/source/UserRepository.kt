package com.muramsyah.mygithubusers.core.data.source

import com.muramsyah.mygithubusers.core.data.source.local.LocalDataSource
import com.muramsyah.mygithubusers.core.data.source.remote.RemoteDataSource
import com.muramsyah.mygithubusers.core.data.source.remote.network.ApiResponse
import com.muramsyah.mygithubusers.core.data.source.remote.response.ListUserResponse
import com.muramsyah.mygithubusers.core.domain.model.User
import com.muramsyah.mygithubusers.core.domain.repository.IUserRepository
import com.muramsyah.mygithubusers.core.utils.AppExecutors
import com.muramsyah.mygithubusers.core.utils.MappingHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    override fun getAllUser(): Flow<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, List<ListUserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUser().map {
                    MappingHelper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ListUserResponse>>> =
                remoteDataSource.getAllListUser()


            override suspend fun saveCallResult(data: List<ListUserResponse>) {
                val userList = MappingHelper.mapResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }

        }.asFlow()
    }

}