package com.muramsyah.mygithubusers.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.muramsyah.mygithubusers.core.data.source.local.entity.ListUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM listUser")
    fun getAllUser(): Flow<List<ListUserEntity>>

}