package com.ofek.sample.data.postitem.api.models

import com.google.gson.annotations.SerializedName

data class ApiPostItem(
    @SerializedName("id") val id: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("created_at_i") val createdAti: Int,
    @SerializedName("type") val type: String,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("text") val text: String,
    @SerializedName("points") val points: Int,
    @SerializedName("parent_id") val parentId: String,
    @SerializedName("story_id") val storyId: String
)