package com.example.picnat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.picnat.data.model.Bookmark

@Database(entities = arrayOf(Bookmark::class),version = 1)
abstract class PlaceBookDatabase: RoomDatabase(){
    abstract fun bookmarkDao(): BookmarkDao

    companion object{
        private var instance: PlaceBookDatabase ?= null

        fun getInstance(context: Context): PlaceBookDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaceBookDatabase::class.java,
                    "PlaceBook"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as PlaceBookDatabase
        }
    }
}