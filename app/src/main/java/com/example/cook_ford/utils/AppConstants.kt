package com.example.cook_ford.utils

import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.preferencesSetKey
import java.util.regex.Pattern

object AppConstants {


    //const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    val PASSWORD_PATTERN: Pattern? = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    const val EMPTY_STRING = ""

    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = preferencesSetKey<String>("auth_key")
}