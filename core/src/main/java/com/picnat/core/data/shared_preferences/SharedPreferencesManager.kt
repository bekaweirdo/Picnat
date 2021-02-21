package com.picnat.core.data.shared_preferences

interface SharedPreferencesManager {

    fun getBoolean(key: String, defaultValue: Boolean) : Boolean

    fun getLong(key: String, defaultValue: Long) : Long

    fun getInt(key: String, defaultValue: Int) : Int

    fun getString(key: String, defaultValue: String?) : String?

    fun writeBoolean(key: String, value: Boolean)

    fun writeLong(key: String, value: Long)

    fun writeInt(key: String, value: Int)

    fun writeString(key: String, value: String)

}