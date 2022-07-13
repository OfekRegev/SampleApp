package com.ofek.sample.data.feed.api.models

import com.google.gson.annotations.SerializedName

class ApiSearchPostItem(
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("points") val points: Int? = null,
    @SerializedName("story_text") val storyText: String? = null,
    @SerializedName("comment_text") val commentText: String? = null,
    @SerializedName("num_comments") val numComments: Int? = null,
    @SerializedName("story_id") val storyId: String? = null,
    @SerializedName("story_title") val storyTitle: String? = null,
    @SerializedName("story_url") val storyUrl: String? = null,
    @SerializedName("parent_id") val parentId: String? = null,
    @SerializedName("created_at_i") val createdAtI: Int? = null,
    @SerializedName("_tags") val _tags: List<String>? = null,
    @SerializedName("objectID") val objectId: String? = null,
)