package com.example.picnat.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.picnat.data.database.BookmarkDao
import com.example.picnat.data.database.PlaceBookDatabase
import com.example.picnat.data.model.Bookmark

class BookmarkRepo(context: Context) {
    private var db = PlaceBookDatabase.getInstance(context)
    private var bookmarDao: BookmarkDao = db.bookmarkDao()

    val allBookmarks: LiveData<List<Bookmark>>
    get() {
        return bookmarDao.loadAll()
    }
}