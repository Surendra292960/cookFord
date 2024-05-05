package com.example.cook_ford.presentation.screens.sign_up

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.request.SignUpRequest
import com.example.cook_ford.data.remote.response.SignUpResponse
import com.example.cook_ford.domain.use_cases.SignUpUseCase
import com.example.cook_ford.presentation.common.widgets.DialogState
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.RegistrationErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.SignUpState
import com.example.cook_ford.presentation.screens.sign_up.state.SignUpUiEvent
import com.example.cook_ford.presentation.screens.sign_up.state.confirmPasswordEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.invalidPasswordErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.nameEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.passwordMismatchErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.userNameEmptyErrorState
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) :ViewModel() {
    var signUpState = mutableStateOf(SignUpState())
        private set

    var dialogState = mutableStateOf(DialogState())
        private set

    private val _response: MutableLiveData<NetworkResult<SignUpResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<SignUpResponse>> = _response


    /**
     * Function called on any login event [SignUpUiEvent]
     */
    fun onUiEvent(signUpUiEvent: SignUpUiEvent) {
        when (signUpUiEvent) {
            // Name id changed event
            is SignUpUiEvent.NameChanged -> {
                signUpState.value = signUpState.value.copy(
                    name = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        nameErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Name empty state
                            nameEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }
            // Email id changed event
            is SignUpUiEvent.UserNameChanged -> {
                signUpState.value = signUpState.value.copy(
                    username = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        userNameErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Email id empty state
                            userNameEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Mobile Number changed event
            is SignUpUiEvent.PhoneChanged -> {
                signUpState.value = signUpState.value.copy(
                    phone = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        phoneErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Mobile Number Empty state
                            phoneEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }

            // Password changed event
            is SignUpUiEvent.PasswordChanged -> {
                signUpState.value = signUpState.value.copy(
                    password = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        passwordErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Password Empty state
                            passwordEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Confirm Password changed event
            is SignUpUiEvent.ConfirmPasswordChanged -> {
                signUpState.value = signUpState.value.copy(
                    confirmPassword = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        confirmPasswordErrorState = when {

                            // Empty state of confirm password
                            signUpUiEvent.inputValue.trim().isEmpty() -> {
                                confirmPasswordEmptyErrorState
                            }

                            // Password is different than the confirm password
                            signUpState.value.password.trim() != signUpUiEvent.inputValue -> {
                                passwordMismatchErrorState
                            }

                            // Valid state
                            else -> ErrorState()
                        }
                    )
                )
            }

            // Submit Registration event
            is SignUpUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    // TODO Trigger registration in authentication flow
                   // signUpState.value = signUpState.value.copy(isSignUpSuccessful = true)
                    makeSigUpRequest(
                        SignUpRequest(
                            name = signUpState.value.name,
                            username = signUpState.value.username,
                            password = signUpState.value.password,
                            phone = signUpState.value.phone,
                            gender = "Male"
                        )
                    )
                }
            }
        }
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (usecase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val nameString = signUpState.value.name.trim()
        val emailString = signUpState.value.username.trim()
        val mobileNumberString = signUpState.value.phone.trim()
        val passwordString = signUpState.value.password.trim()
        val confirmPasswordString = signUpState.value.confirmPassword.trim()

        if (// Name empty
            nameString.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = RegistrationErrorState(
                    nameErrorState = nameEmptyErrorState
                )
            )
            return false
        }

        // Email empty
        if (emailString.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = RegistrationErrorState(
                    userNameErrorState = userNameEmptyErrorState
                )
            )
            return false
        }

        // Email Matcher
        if (emailString.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
                signUpState.value = signUpState.value.copy(
                    errorState = RegistrationErrorState(
                        userNameErrorState = invalidUserNameErrorState
                    )
                )
                return false
            }
        }

        //Mobile Number Empty
        if (mobileNumberString.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = RegistrationErrorState(
                    phoneErrorState = phoneEmptyErrorState
                )
            )
            return false
        }

        //Password Empty
        if (passwordString.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = RegistrationErrorState(
                    passwordErrorState = passwordEmptyErrorState
                )
            )
            return false
        }

        //Password Matcher
        if (passwordString.isNotEmpty()) {
            if (!AppConstants.PASSWORD_PATTERN?.matcher(passwordString)!!.matches()){
                signUpState.value = signUpState.value.copy(
                    errorState = RegistrationErrorState(
                        passwordErrorState = invalidPasswordErrorState
                    )
                )
                return false
            }else{
                return true
            }
        }

        //Confirm Password Empty
        if (confirmPasswordString.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = RegistrationErrorState(
                    confirmPasswordErrorState = confirmPasswordEmptyErrorState
                )
            )
            return false
        }

        // Password and Confirm Password are different
        if (passwordString != confirmPasswordString) {
            signUpState.value = signUpState.value.copy(
                errorState = RegistrationErrorState(
                    confirmPasswordErrorState = passwordMismatchErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            signUpState.value = signUpState.value.copy(errorState = RegistrationErrorState())
            return true
        }
    }

    private fun makeSigUpRequest(signUpRequest: SignUpRequest) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeSignUpRequest SignUpRequest: ${Gson().toJson(signUpRequest)}")
        signUpUseCase.invoke(signUpRequest).collect { values ->
            if (values.status==true){
                Log.d("TAG", "makeSignUpRequest SignUpResponse: ${Gson().toJson(values)}")
                //values.data?._id?.let { dataStore.saveAuthId(it) }
                dialogState.value = dialogState.value.copy(showDialogState = values.status, message = values.data!!.message)
                signUpState.value = signUpState.value.copy(isSignUpSuccessful = true)
                _response.postValue(values)
            }
        }
    }
}