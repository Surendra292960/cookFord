package com.example.cook_ford.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AuthApp : Application() {
    var instance:AuthApp? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getApp(): AuthApp? {
        return instance
    }
}