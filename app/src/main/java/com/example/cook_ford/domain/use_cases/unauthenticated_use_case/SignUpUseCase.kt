package com.example.cook_ford.domain.use_cases.unauthenticated_use_case

import android.util.Log
import android.util.Patterns
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.data.repository.UnAuthRepositoryImpl
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.SignUpErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.SignUpState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.confirmPasswordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.invalidPasswordErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.passwordMismatchErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.usernameEmptyErrorState
import com.example.cook_ford.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: UnAuthRepositoryImpl) {

    suspend operator fun invoke(signUpRequest: SignUpRequest): Flow<NetworkResult<SignUpResponse>> {
        return repository.signUp(signUpRequest)
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (useCase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    operator fun invoke(signUpState: MutableStateFlow<SignUpState>): Boolean {
        val username = signUpState.value.username.trim()
        val email = signUpState.value.email.trim()
        val password = signUpState.value.password.trim()
        val confirmPassword = signUpState.value.confirmPassword.trim()
        val gender = signUpState.value.gender.trim()

        Log.d("TAG", "validateInputs: $username")
        // Name empty
        if (username.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    usernameErrorState = usernameEmptyErrorState
                )
            )
            return false
        }

        // Email empty
        if (email.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    emailErrorState = emailEmptyErrorState
                )
            )
            return false
        }

        // Email Matcher
        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    emailErrorState = invalidUserNameErrorState
                )
            )
            return false
        }

        //Password Empty
        if (password.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    passwordErrorState = passwordEmptyErrorState
                )
            )
            return false
        }

        //Password Matcher
        if (password.isNotEmpty() &&!AppConstants.PASSWORD_PATTERN?.matcher(password)!!.matches()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    passwordErrorState = invalidPasswordErrorState
                )
            )
            return false
        }

        //Confirm Password Empty
        if (confirmPassword.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    confirmPasswordErrorState = confirmPasswordEmptyErrorState
                )
            )
            return false
        }

        // Password and Confirm Password are different
        if (password != confirmPassword) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    confirmPasswordErrorState = passwordMismatchErrorState
                )
            )
            return false
        }

        //Gender Empty
        if (gender.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            signUpState.value = signUpState.value.copy(errorState = SignUpErrorState())
            return true
        }
    }
}