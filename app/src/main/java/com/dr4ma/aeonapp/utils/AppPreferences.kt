package com.dr4ma.aeonapp.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val PREF = "preferences"
    private const val TOKEN = "token"

    private lateinit var mPreferences: SharedPreferences

    fun getPreferences(context: Context): SharedPreferences {
        mPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return mPreferences
    }

    fun rememberToken(token: String?) {
        mPreferences.edit().putString(TOKEN, token).apply()
    }

    fun findToken(): String? {
        return mPreferences.getString(TOKEN, null)
    }
}