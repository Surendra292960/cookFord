package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant.AUTH_ID
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.domain.use_cases.unauthenticated_use_case.SignUpUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.SignUpErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.SignUpState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.SignUpUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.confirmPasswordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.passwordMismatchErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.usernameEmptyErrorState
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

    private val _signUpState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    private val _showDialog = MutableStateFlow(true)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _location = MutableStateFlow(Pair(0.0, 0.0))

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

    fun setLocation(location: Location) {
        _location.value = Pair(location.latitude, location.longitude)
        Log.d("TAG", "Location Data : ${_location.value.first}, ${_location.value.second}")
    }

    /**
     * Function called on any login event [SignUpUiEvent]
     */
    fun onUiEvent(signUpUiEvent: SignUpUiEvent) {
        when (signUpUiEvent) {

            // UserName id changed event
            is SignUpUiEvent.UserNameChanged -> {
                _signUpState.value = _signUpState.value.copy(
                    username = signUpUiEvent.inputValue,
                    errorState = _signUpState.value.errorState.copy(
                        usernameErrorState = if (signUpUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            usernameEmptyErrorState
                    )
                )
            }


            // Email id changed event
            is SignUpUiEvent.EmailChanged -> {
                _signUpState.value = _signUpState.value.copy(
                    email = signUpUiEvent.inputValue,
                    errorState = _signUpState.value.errorState.copy(
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

            // Password changed event
            is SignUpUiEvent.PasswordChanged -> {
                _signUpState.value = _signUpState.value.copy(
                    password = signUpUiEvent.inputValue,
                    errorState = _signUpState.value.errorState.copy(
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
                _signUpState.value = _signUpState.value.copy(
                    confirmPassword = signUpUiEvent.inputValue,
                    errorState = _signUpState.value.errorState.copy(
                        confirmPasswordErrorState = when {
                            // Empty state of confirm password
                            signUpUiEvent.inputValue.trim().isEmpty() -> {
                                confirmPasswordEmptyErrorState
                            }
                            // Password is different than the confirm password
                            _signUpState.value.password.trim() != signUpUiEvent.inputValue -> {
                                passwordMismatchErrorState
                            }
                            // Valid state
                            else -> ErrorState()
                        }
                    )
                )
            }

            // Gender id changed event
            is SignUpUiEvent.GenderChange -> {
                _signUpState.value = _signUpState.value.copy(
                    gender = signUpUiEvent.inputValue,
                    errorState = _signUpState.value.errorState.copy(
                        genderErrorState = if (signUpUiEvent.inputValue.trim().isEmpty()) {
                            // Email id empty state
                            genderSelectionErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Submit SignUp event
            is SignUpUiEvent.Submit -> {
                val inputsValidated = signUpUseCase.invoke(_signUpState)
                Log.d("TAG", "SignUpScreen: onSubmit $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger registration in authentication flow
                    Log.d("TAG", "onUiEvent: ${Gson().toJson(_signUpState.value)}")
                    /*userSession.getString(SessionConstant.PHONE_NUMBER)?.let { phone->
                        SignUpRequest(
                            username = _signUpState.value.username,
                            email = _signUpState.value.email,
                            password = _signUpState.value.password,
                            gender = _signUpState.value.gender,
                            phone = phone,
                            latitude = _location.value.first.toString(),
                            longitude = _location.value.second.toString()
                        )
                    }?.let { signupRequest->
                        Log.d("TAG", "onUiEvent Data : ${Gson().toJson(signupRequest)}")
                        makeSigUpRequest(
                            signupRequest
                        )
                    }*/

                    makeSigUpRequest(
                        SignUpRequest(
                            username = _signUpState.value.username,
                            email = _signUpState.value.email,
                            password = _signUpState.value.password,
                            gender = _signUpState.value.gender,
                            phone = "8755092960",
                            userType = _signUpState.value.userType,
                            latitude = _location.value.first.toString(),
                            longitude = _location.value.second.toString()
                        )
                    )
                }
            }
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
                            _signUpState.emit(_signUpState.value.copy(signUpResponse = result.data))
                            _signUpState.value = _signUpState.value.copy(isSignUpSuccessful = true)
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

    fun setUserType(userType: String) {
        Log.d("TAG", "setUserType: $userType")
        _signUpState.value = _signUpState.value.copy(userType = userType, errorState = SignUpErrorState())
        Log.d("TAG", "setUserType data: ${_signUpState.value.userType}")
    }
}