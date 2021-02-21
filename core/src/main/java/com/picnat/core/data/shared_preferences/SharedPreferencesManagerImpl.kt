package com.picnat.core.data.shared_preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManagerImpl(private val context: Context) : SharedPreferencesManager {

    companion object{
        const val PREF_NAME = "Picnat.app"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    override fun getBoolean(key: String, defaultValue: Boolean) = preferences.getBoolean(key, defaultValue)

    override fun getLong(key: String, defaultValue: Long) = preferences.getLong(key, defaultValue)

    override fun getInt(key: String, defaultValue: Int) = preferences.getInt(key, defaultValue)

    override fun getString(key: String, defaultValue: String?) = preferences.getString(key, defaultValue)

    override fun writeBoolean(key: String, value: Boolean) = editor.putBoolean(key, value).apply()

    override fun writeLong(key: String, value: Long) = editor.putLong(key, value).apply()

    override fun writeInt(key: String, value: Int) = editor.putInt(key, value).apply()

    override fun writeString(key: String, value: String) = editor.putString(key, value).apply()
}