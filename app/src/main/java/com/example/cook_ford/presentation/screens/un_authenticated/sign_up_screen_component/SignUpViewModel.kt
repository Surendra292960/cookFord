package com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant.AUTH_ID
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.domain.use_cases.SignUpUseCase
import com.example.cook_ford.presentation.component.widgets.dialog.DialogState
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.SignUpErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.SignUpState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.SignUpUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.confirmPasswordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.invalidPasswordErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.passwordMismatchErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.state.usernameEmptyErrorState
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase, private val userSession: UserSession) :ViewModel() {
    var signUpState = mutableStateOf(SignUpState())
        private set

    var dialogState = mutableStateOf(DialogState())
        private set

    private val _signUpResponse: MutableStateFlow<SignUpResponse> = MutableStateFlow(SignUpResponse())
    val signUpResponse: StateFlow<SignUpResponse> = _signUpResponse.asStateFlow()

    private val _showDialog = MutableStateFlow(true)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _onProcessSuccess = MutableSharedFlow<String>()
    val onProcessSuccess = _onProcessSuccess.asSharedFlow()

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        _showDialog.value = false
    }

    fun onDialogDismiss() {
        Log.d("TAG", "onDialogDismiss: ")
        _showDialog.value = false
    }

    /**
     * Function called on any signUp event [SignUpUiEvent]
     */
    fun onUiEvent(signUpUiEvent: SignUpUiEvent) {
        when (signUpUiEvent) {
            // Name id changed event
            is SignUpUiEvent.UserNameChanged -> {
                signUpState.value = signUpState.value.copy(
                    username = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        usernameErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Name empty state
                            usernameEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }
            // Email id changed event
            is SignUpUiEvent.EmailChanged -> {
                signUpState.value = signUpState.value.copy(
                    email = signUpUiEvent.inputValue,
                    errorState = signUpState.value.errorState.copy(
                        emailErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Email id empty state
                            emailEmptyErrorState
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
                    makeSigUpRequest(
                        SignUpRequest(
                            username = signUpState.value.username,
                            email = signUpState.value.email,
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
        val username = signUpState.value.username.trim()
        val email = signUpState.value.email.trim()
        val phone = signUpState.value.phone.trim()
        val password = signUpState.value.password.trim()
        val confirmPassword = signUpState.value.confirmPassword.trim()
        Log.d("TAG", "validateInputs: $confirmPassword")

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

        //Mobile Number Empty
        if (phone.isEmpty()) {
            signUpState.value = signUpState.value.copy(
                errorState = SignUpErrorState(
                    phoneErrorState = phoneEmptyErrorState
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

        // No errors
        else {
            // Set default error state
            signUpState.value = signUpState.value.copy(errorState = SignUpErrorState())
            return true
        }
    }

    private fun makeSigUpRequest(signUpRequest: SignUpRequest) = viewModelScope.launch(Dispatchers.IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        Log.d("TAG", "makeSignUpRequest SignUpRequest: ${Gson().toJson(signUpRequest)}")
        signUpUseCase.invoke(signUpRequest).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status==true){
                        Log.d("TAG", "makeSignUpRequest SignUpResponse: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _signUpResponse.emit(result.data)
                            signUpState.value = signUpState.value.copy(isSignUpSuccessful = true)
                            //TODO save token after dialog dismiss
                            userSession.put(AUTH_ID, response._id)
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                        Log.d("TAG", "makeSignUpRequest SignUpResponse: ${userSession.getString(AUTH_ID)}")
                    }
                }
                is NetworkResult.Error->{

                    Log.d("TAG", "makeSignUpRequest SignUpResponse: ${result.message}")
                     _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeSignUpRequest SignUpResponse : Loading")
                }
            }
        }
    }
}