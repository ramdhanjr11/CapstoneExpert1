package com.muramsyah.core.utils

import com.muramsyah.core.data.source.local.entity.UserEntity
import com.muramsyah.core.data.source.remote.response.DetailUserResponse
import com.muramsyah.core.data.source.remote.response.ListUserResponse
import com.muramsyah.core.domain.model.DetailUser
import com.muramsyah.core.domain.model.User

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
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: User): UserEntity = UserEntity(
        login = input.login,
        id = input.id,
        avatarUrl = input.avatarUrl,
        url = input.url,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        isFavorite = input.isFavorite
    )

    fun mapDetailResponseToDetailUser(input: DetailUserResponse): DetailUser = DetailUser(
        login = input.login,
        id = input.id,
        avatarUrl = input.avatarUrl,
        url = input.url,
        followers = input.followers,
        following = input.following,
        bio = input.bio,
        blog = input.blog,
        company = input.company,
        publicRepos = input.publicRepos,
        name = input.name,
        location = input.location,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl
    )

    fun mapDetailUserToUser(input: DetailUser): User = User(
        login = input.login!!,
        id = input.id!!,
        avatarUrl = input.avatarUrl!!,
        url = input.url!!,
        followersUrl = input.followersUrl!!,
        followingUrl = input.followingUrl!!,
        isFavorite = false
    )
}