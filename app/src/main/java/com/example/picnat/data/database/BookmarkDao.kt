package com.example.picnat.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.picnat.data.model.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM Bookmark")
    fun loadAll(): LiveData<List<Bookmark>>
}