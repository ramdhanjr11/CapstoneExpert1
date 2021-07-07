package com.muramsyah.core.data.source.local.room

import androidx.room.*
import com.muramsyah.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<UserEntity>)

    @Update
    fun updateFavoriteUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE isFavorite = 1")
    fun getFavoriteUsers(): Flow<List<UserEntity>>
}