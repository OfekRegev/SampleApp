package com.ofek.sample.data.localdb.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LocalDbPost(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "postId") val postId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "points") val points: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "views") val views: Int,
)