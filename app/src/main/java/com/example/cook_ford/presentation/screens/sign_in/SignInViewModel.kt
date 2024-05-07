package com.example.cook_ford.presentation.screens.sign_in
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant.ACCESS_TOKEN
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.request.SignInRequest
import com.example.cook_ford.data.remote.response.SignInResponse
import com.example.cook_ford.domain.use_cases.SignInUseCase
import com.example.cook_ford.presentation.common.widgets.DialogState
import com.example.cook_ford.presentation.screens.sign_in.state.DialogEvent
import com.example.cook_ford.presentation.screens.sign_in.state.ErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInState
import com.example.cook_ford.presentation.screens.sign_in.state.SignInUiEvent
import com.example.cook_ford.presentation.screens.sign_in.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_in.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.invalidPasswordErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.sign_up.state.userNameEmptyErrorState
import com.example.cook_ford.utils.AppConstants.PASSWORD_PATTERN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase, private val userSession: UserSession) : ViewModel() {

    var signInState = mutableStateOf(SignInState())
        private set

    var dialogState = mutableStateOf(DialogState())
        private set

    private val _response: MutableLiveData<NetworkResult<SignInResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<SignInResponse>> = _response

    private val _responseError: MutableLiveData<String> = MutableLiveData()
    val responseError: LiveData<String> = _responseError

    fun onDialogEvent(dialogEvent: DialogEvent){
        when(dialogEvent){
            is DialogEvent.DismissDialog->{
                dialogState.value = dialogState.value.copy(
                    dismissDialogState = dialogEvent.inputValue
                )
                Log.d("TAG", "onDialogEvent: ${dialogState.value.dismissDialogState}")
            }
        }
    }
    /**
     * Function called on any login event [SignInUiEvent]
     */
    fun onUiEvent(signInUiEvent: SignInUiEvent) {
        when (signInUiEvent) {

            //Mobile changed
            is SignInUiEvent.PhoneChanged -> {
                signInState.value = signInState.value.copy(
                    username = signInUiEvent.inputValue,
                    errorState = signInState.value.errorState.copy(
                        phoneErrorState = if (signInUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            phoneEmptyErrorState
                    )
                )
            }
            // Email changed
            is SignInUiEvent.UserNameChanged -> {
                signInState.value = signInState.value.copy(
                    username = signInUiEvent.inputValue,
                    errorState = signInState.value.errorState.copy(
                        userNameErrorState = if (signInUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            userNameEmptyErrorState
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
                    makeSigInRequest(
                        SignInRequest(
                            username = signInState.value.username,
                            password = signInState.value.password
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

        val emailString = signInState.value.username.trim()
        val passwordString = signInState.value.password

        // Email empty
        if (emailString.isEmpty()) {
            signInState.value = signInState.value.copy(
                errorState = SignInErrorState(
                    userNameErrorState = userNameEmptyErrorState
                )
            )
            return false
        }
        // Email Matcher
        if (emailString.isNotEmpty()) {
           if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
               signInState.value = signInState.value.copy(
                   errorState = SignInErrorState(
                       userNameErrorState = invalidUserNameErrorState
                   )
               )
               return false
           }
        }

        //Password Empty
         if (passwordString.isEmpty()) {
            signInState.value = signInState.value.copy(
                errorState = SignInErrorState(
                    passwordErrorState = passwordEmptyErrorState
                )
            )
            return false
        }

        //Password Matcher
        if (passwordString.isNotEmpty()) {
            if (!PASSWORD_PATTERN?.matcher(passwordString)!!.matches()){
                signInState.value = signInState.value.copy(
                    errorState = SignInErrorState(
                        passwordErrorState = invalidPasswordErrorState
                    )
                )
                return false
            }else{
                return true
            }
        }

        // No errors
        else {
            // Set default error state
            signInState.value = signInState.value.copy(errorState = SignInErrorState())
            return true
        }
    }

    private fun makeSigInRequest(signInRequest: SignInRequest) = viewModelScope.launch(IO) {
        signInUseCase.invoke(signInRequest).collect { values ->
            when(values){
                is NetworkResult.Success->{
                    if (values.status == true){
                        dialogState.value = dialogState.value.copy(showDialogState = values.status, message = values.data!!.message)
                        signInState.value = signInState.value.copy(isSignInSuccessful = values.status)
                        userSession.put(ACCESS_TOKEN, values.data.accessToken)
                        _response.postValue(values)
                        Log.d("TAG", "makeSigInRequest: ${userSession.getString(ACCESS_TOKEN)}")
                    }
                }
                is NetworkResult.Error->{
                    _responseError.postValue(values.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeSigInRequest: Loading")
                }
            }
        }
    }
}