package com.example.cook_ford.domain

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import com.example.cook_ford.presentation.screens.sign_in.state.SignInErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInState
import com.example.cook_ford.presentation.screens.sign_in.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.invalidUserNameErrorState

class ValidateEmail {
    var loginState = mutableStateOf(SignInState())
    fun execute(email: String): Any {
        return if(email.isBlank()) {
             loginState.value = loginState.value.copy(
                errorState = SignInErrorState(
                    emailErrorState = emailEmptyErrorState
                )
            )
            false
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginState.value = loginState.value.copy(
                errorState = SignInErrorState(
                    emailErrorState = invalidUserNameErrorState
                )
            )
            false
        } else {
            true
        }
    }
}