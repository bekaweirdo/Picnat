package com.picnat.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TestEntity::class], version = 1)
abstract class PicnatDatabase : RoomDatabase() {
//    abstract fun picnatDAO(): PicnatDAO
}