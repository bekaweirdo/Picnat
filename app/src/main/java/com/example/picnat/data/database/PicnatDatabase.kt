package com.example.picnat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.picnat.data.model.Bookmark

@Database(entities = arrayOf(Bookmark::class), version = 1)
abstract class PicnatDatabase : RoomDatabase() {
    // 2
    abstract fun bookmarkDao(): BookmarkDao
    // 3
    companion object {
        // 4
        private var instance: PicnatDatabase? = null
        // 5
        fun getInstance(context: Context): PicnatDatabase {
            if (instance == null) {
// 6
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PicnatDatabase::class.java,
                    "Picnat").build()
            }
// 7
            return instance as PicnatDatabase
        }
    }
}