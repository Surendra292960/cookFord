package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant.ACCESS_TOKEN
import com.example.cook_ford.data.local.SessionConstant.USER_ID
import com.example.cook_ford.data.local.SessionConstant.USER_TYPE
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.domain.use_cases.unauthenticated_use_case.SignInUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.passwordEmptyErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase, private val userSession: UserSession) : ViewModel() {

    private val _signInState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState> = _signInState.asStateFlow()

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
     * Function called on any login event [SignInUiEvent]
     */
    fun onUiEvent(signInUiEvent: SignInUiEvent) {
        when (signInUiEvent) {

            // Email changed
            is SignInUiEvent.EmailChanged -> {
                _signInState.value = _signInState.value.copy(
                    email = signInUiEvent.inputValue,
                    errorState = _signInState.value.errorState.copy(
                        emailErrorState = if (signInUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            // Password changed
            is SignInUiEvent.PasswordChanged -> {
                _signInState.value = _signInState.value.copy(
                    password = signInUiEvent.inputValue,
                    errorState = _signInState.value.errorState.copy(
                        passwordErrorState = if (signInUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            // Submit signIn
            is SignInUiEvent.Submit -> {
                val inputsValidated = signInUseCase.invoke(_signInState)
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger login in authentication flow
                    makeSigInRequest(SignInRequest(email = _signInState.value.email, password = _signInState.value.password))
                }
            }
        }
    }

    private fun makeSigInRequest(signInRequest: SignInRequest) = viewModelScope.launch(IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        Log.d("TAG", "makeSigInRequest SignInResponse: ${Gson().toJson(signInRequest)}")
        signInUseCase.invoke(signInRequest).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        Log.d("TAG", "makeSigInRequest SignInResponse: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _signInState.emit(signInState.value.copy(signInResponse = response))
                            _signInState.value = _signInState.value.copy(isSignInSuccessful = result.status)
                            //TODO save token after dialog dismiss
                            userSession.put(ACCESS_TOKEN, response.accessToken)
                            userSession.put(USER_TYPE, response.userType)
                            userSession.put(USER_ID, response.user_id)
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeSigInRequest: ${result.message}")
                    _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeSigInRequest: Loading")
                }
            }
        }
    }
}