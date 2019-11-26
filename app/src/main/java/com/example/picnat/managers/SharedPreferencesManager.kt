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

    fun saveSplashLauncher(){
        mEditor.putString(PARAM_SPLASH_LAUNCHER,"1").apply()
    }

    fun getSplashLauncher(): String? {
        return mPref.getString(PARAM_SPLASH_LAUNCHER,"0")
    }
    companion object{
        private val PREF_NAME = "PicnatPref"
        private val PARAM_SPLASH_LAUNCHER = "launcher_splash"
    }
}