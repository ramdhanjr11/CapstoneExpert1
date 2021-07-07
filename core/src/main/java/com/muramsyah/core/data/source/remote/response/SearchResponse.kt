package com.muramsyah.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("items")
    val items: List<ListUserResponse>
)
