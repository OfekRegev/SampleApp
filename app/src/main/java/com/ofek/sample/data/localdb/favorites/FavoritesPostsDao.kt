package com.ofek.sample.data.localdb.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ofek.sample.data.localdb.models.LocalDbPost

@Dao
interface FavoritesPostsDao {

    @Query("SELECT * FROM localdbpost WHERE postId = :postId")
    fun getPost(postId: String): LocalDbPost

    @Query("SELECT EXISTS(SELECT * FROM localdbpost WHERE postId = :postId)")
    fun exists(postId: String): Boolean

//    @Query("SELECT * FROM localdbpost")
//    fun getAllPosts(skip: Int, count: Int): List<LocalDbPost>

    @Query("SELECT * FROM localdbpost")
    fun getAllPosts(): List<LocalDbPost>

    @Insert
    fun insertPostItem(dbItem: LocalDbPost)


}