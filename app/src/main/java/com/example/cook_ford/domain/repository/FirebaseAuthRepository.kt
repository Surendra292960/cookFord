package com.example.cook_ford.domain.repository

import android.app.Activity
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.state.ResultState
import kotlinx.coroutines.flow.Flow


interface FirebaseAuthRepository {
    fun createUserWithPhone(
        phone:String,
        activity: Activity
    ) : Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ): Flow<ResultState<String>>

}