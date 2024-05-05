package com.example.cook_ford.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AuthApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}