package com.muramsyah.mygithubusers.core.data.source.local.room

import androidx.room.*
import com.muramsyah.mygithubusers.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<UserEntity>)

    @Update
    fun updateFavoriteUser(user: UserEntity)
}