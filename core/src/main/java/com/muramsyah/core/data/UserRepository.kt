package com.muramsyah.core.data

import android.util.Log
import com.muramsyah.core.data.source.local.LocalDataSource
import com.muramsyah.core.data.source.remote.RemoteDataSource
import com.muramsyah.core.data.source.remote.network.ApiResponse
import com.muramsyah.core.data.source.remote.response.ListUserResponse
import com.muramsyah.core.domain.model.DetailUser
import com.muramsyah.core.domain.model.User
import com.muramsyah.core.domain.repository.IUserRepository
import com.muramsyah.core.utils.AppExecutors
import com.muramsyah.core.utils.MappingHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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

            override suspend fun createCall(): Flow<ApiResponse<List<ListUserResponse>>> =
                remoteDataSource.getAllListUser()


            override suspend fun saveCallResult(data: List<ListUserResponse>) {
                val userList = MappingHelper.mapResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }

        }.asFlow()
    }

    override fun getDetailUser(username: String): Flow<Resource<DetailUser>> {
        Log.d("UserRepository", "empty data")
        return flow<Resource<DetailUser>> {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getDetailUser(username).first()) {
                is ApiResponse.Success -> {
                    Log.d("UserRepository", apiResponse.data.name.toString())
                    emit(
                        Resource.Success(
                            MappingHelper.mapDetailResponseToDetailUser(
                                apiResponse.data
                            )
                        )
                    )
                }
                is ApiResponse.Empty -> {
                    Log.d("UserRepository", "empty data")
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error("Error get data from api!"))
                }
            }
        }
    }

    override fun setFavoriteUser(detailUser: DetailUser, newState: Boolean) {
        val mDetailUser = MappingHelper.mapDetailUserToUser(detailUser)
        val userEntity = MappingHelper.mapDomainToEntity(mDetailUser)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, newState) }
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        return localDataSource.getFavoriteUsers().map {
            MappingHelper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun getSearchUser(username: String): List<User> {
        val resultUser = ArrayList<User>()
        when (val apiResponse = remoteDataSource.getSearchUser(username).first()) {
            is ApiResponse.Success -> {
                Log.d("UserRepository", apiResponse.data.size.toString())
                val userEntities = MappingHelper.mapResponsesToEntities(apiResponse.data)
                val user = MappingHelper.mapEntitiesToDomain(userEntities)
                resultUser.addAll(user)
            }
            is ApiResponse.Empty -> {
                Log.d("UserRepository", "empty data")
            }
            is ApiResponse.Error -> {
                Log.d("UserRepository", "Error get data search user from api!")
            }
        }
        return resultUser
    }

}