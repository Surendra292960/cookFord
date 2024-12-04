package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant.ACCESS_TOKEN
import com.example.cook_ford.data.local.SessionConstant.USER_ID
import com.example.cook_ford.data.local.SessionConstant.USER_TYPE
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.domain.use_cases.SignInUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.SignInUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.cook_sign_up.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.cook_sign_up.state.invalidPasswordErrorState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.cook_component.account_component.cook_sign_up.state.invalidUserNameErrorState
import com.example.cook_ford.utils.AppConstants.PASSWORD_PATTERN
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

    var signInState = mutableStateOf(SignInState())

    private val _signInResponse: MutableStateFlow<SignInResponse> = MutableStateFlow(SignInResponse())
    val signInResponse: StateFlow<SignInResponse> = _signInResponse.asStateFlow()

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
                signInState.value = signInState.value.copy(
                    email = signInUiEvent.inputValue,
                    errorState = signInState.value.errorState.copy(
                        emailErrorState = if (signInUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            // Password changed
            is SignInUiEvent.PasswordChanged -> {
                signInState.value = signInState.value.copy(
                    password = signInUiEvent.inputValue,
                    errorState = signInState.value.errorState.copy(
                        passwordErrorState = if (signInUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            // Submit signIn
            is SignInUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger login in authentication flow
                    makeSigInRequest(SignInRequest(email = signInState.value.email, password = signInState.value.password))
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

    private fun makeSigInRequest(signInRequest: SignInRequest) = viewModelScope.launch(IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        Log.d("TAG", "signInRequest SignInResponse: ${Gson().toJson(signInRequest)}")
        signInUseCase.invoke(signInRequest).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        Log.d("TAG", "signInRequest SignInResponse: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _signInResponse.emit(response)
                            signInState.value = signInState.value.copy(isSignInSuccessful = result.status)
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