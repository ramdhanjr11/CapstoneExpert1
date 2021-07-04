package com.muramsyah.mygithubusers.core.utils

import com.muramsyah.mygithubusers.core.data.source.local.entity.UserEntity
import com.muramsyah.mygithubusers.core.data.source.remote.response.DetailUserResponse
import com.muramsyah.mygithubusers.core.data.source.remote.response.ListUserResponse
import com.muramsyah.mygithubusers.core.domain.model.DetailUser
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
        location = input.location
    )
}