package com.muramsyah.mygithubusers.core.utils

import com.muramsyah.mygithubusers.core.data.source.local.entity.UserEntity
import com.muramsyah.mygithubusers.core.data.source.remote.response.ListUserResponse
import com.muramsyah.mygithubusers.core.domain.model.User

object MappingHelper {
    fun mapResponsesToEntities(input: List<ListUserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val userEntity = UserEntity(
                login = it.login,
                id = it.id,
                avatarUrl = it.avatarUrl,
                url = it.url,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                isFavorite = false
            )
            userList.add(userEntity)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                login = it.login,
                id = it.id,
                avatarUrl = it.avatarUrl,
                url = it.url,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                isFavorite = false
            )
        }

    fun mapDomainToEntity(input: User): UserEntity = UserEntity(
        login = input.login,
        id = input.id,
        avatarUrl = input.avatarUrl,
        url = input.url,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        isFavorite = false
    )
}