package com.example.cook_ford.domain.use_cases.unauthenticated_use_case

import android.util.Patterns
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.data.repository.UnAuthRepositoryImpl
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.invalidPasswordErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.invalidUserNameErrorState
import com.example.cook_ford.utils.AppConstants.PASSWORD_PATTERN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: UnAuthRepositoryImpl) {
    
    suspend operator fun invoke(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>> {
        return repository.signIn(signInRequest)
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (useCase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    operator fun invoke (signInState: MutableStateFlow<SignInState>): Boolean {
        val email = signInState.value.email.trim()
        val password = signInState.value.password.trim()

        // Email empty
        if (email.isEmpty()) {
            signInState.value = signInState.value.copy(
                errorState = SignInErrorState(
                    emailErrorState = emailEmptyErrorState
                )
            )
            return false
        }
        // Email Matcher
        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInState.value = signInState.value.copy(
                errorState = SignInErrorState(
                    emailErrorState = invalidUserNameErrorState
                )
            )
            return false
        }

        //Password Empty
        if (password.isEmpty()) {
            signInState.value = signInState.value.copy(
                errorState = SignInErrorState(
                    passwordErrorState = passwordEmptyErrorState
                )
            )
            return false
        }

        //Password Matcher
        if (password.isNotEmpty() && !PASSWORD_PATTERN?.matcher(password)!!.matches()) {
            signInState.value = signInState.value.copy(
                errorState = SignInErrorState(
                    passwordErrorState = invalidPasswordErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            signInState.value = signInState.value.copy(errorState = SignInErrorState())
            return true
        }
    }
}