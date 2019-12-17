package com.example.picnat.managers

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(context: Context){
    private val mPref: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    init {
        mPref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        mEditor = mPref.edit()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean){
        mEditor.putBoolean(PARAM_SPLASH_LAUNCHER,isFirstTime).apply()
    }

    fun isFirstTimeLaunch(): Boolean {
        return mPref.getBoolean(PARAM_SPLASH_LAUNCHER,true)
    }
    companion object{
        private val PREF_NAME = "PicnatPref"
        private val PARAM_SPLASH_LAUNCHER = "launcher_splash"
    }
}