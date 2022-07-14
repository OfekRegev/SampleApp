package com.ofek.sample.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ofek.sample.data.localdb.favorites.FavoritesPostsDao
import com.ofek.sample.data.localdb.models.LocalDbPost

@Database(entities = [LocalDbPost::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): FavoritesPostsDao
}