package com.example.picnat.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.picnat.data.database.BookmarkDao
import com.example.picnat.data.database.PicnatDatabase
import com.example.picnat.data.model.Bookmark

class BookmarkRepo(context: Context) {
    private var db = PicnatDatabase.getInstance(context)
    private var bookmarDao: BookmarkDao = db.bookmarkDao()

    fun addBookmark(bookmark: Bookmark): Long?{
        val newId = bookmarDao.insertBookmark(bookmark)
        bookmark.id=newId
        return newId
    }

    fun createBookmark(): Bookmark{
        return Bookmark()
    }
    val allBookmarks: LiveData<List<Bookmark>>
    get() {
        return bookmarDao.loadAll()
    }

}