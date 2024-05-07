package com.example.cook_ford.data.local

import android.content.Context
import android.content.SharedPreferences

class UserSession (context: Context) {

    private val PREFS_NAME = "sharedpref12345"
    private var sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPref.edit()

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun check(key: String):Boolean{
        return sharedPref.contains(key)
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }

}