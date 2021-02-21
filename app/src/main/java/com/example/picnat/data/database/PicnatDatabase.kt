package com.example.picnat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TestEntity::class], version = 1)
abstract class PicnatDatabase : RoomDatabase() {
    companion object {
        private var instance: PicnatDatabase? = null
        fun getInstance(context: Context): PicnatDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PicnatDatabase::class.java,
                    "Picnat").build()
            }
            return instance as PicnatDatabase
        }
    }
}