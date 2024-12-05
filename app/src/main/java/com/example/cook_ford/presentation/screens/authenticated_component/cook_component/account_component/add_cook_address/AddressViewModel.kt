package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address

import android.location.Location
import android.util.Log
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
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.AddressErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.AddressState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.AddressUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.addressEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.cityEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.stateEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.zipCodeEmptyErrorState
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
class AddressViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase, private val userSession: UserSession) :ViewModel() {
    var addressState = mutableStateOf(AddressState())

    var dialogState = mutableStateOf(DialogState())
        private set

    private val _signUpResponse: MutableStateFlow<SignUpResponse> = MutableStateFlow(SignUpResponse())
    val signUpResponse: StateFlow<SignUpResponse> = _signUpResponse.asStateFlow()

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
     * Function called on any signUp event [AddressUiEvent]
     */
    fun onUiEvent(addressUiEvent: AddressUiEvent) {
        when (addressUiEvent) {
            // Address id changed event
            is AddressUiEvent.AddressChange -> {
                addressState.value = addressState.value.copy(
                    address = addressUiEvent.inputValue,
                    errorState = addressState.value.errorState.copy(
                        addressErrorState = if (addressUiEvent.inputValue.trim().isEmpty()) {
                            // Address empty state
                            addressEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }
            // City id changed event
            is AddressUiEvent.CityChange -> {
                addressState.value = addressState.value.copy(
                    city = addressUiEvent.inputValue,
                    errorState = addressState.value.errorState.copy(
                        cityErrorState = if (addressUiEvent.inputValue.trim().isEmpty()) {
                            // City id empty state
                            cityEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // State changed event
            is AddressUiEvent.StateChange -> {
                addressState.value = addressState.value.copy(
                    state = addressUiEvent.inputValue,
                    errorState = addressState.value.errorState.copy(
                        stateErrorState = if (addressUiEvent.inputValue.trim().isEmpty()) {
                            // State Empty state
                            stateEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // ZipCode id changed event
            is AddressUiEvent.ZipCodeChange -> {
                addressState.value = addressState.value.copy(
                    zipCode = addressUiEvent.inputValue,
                    errorState = addressState.value.errorState.copy(
                        zipCodeErrorState = if (addressUiEvent.inputValue.trim().isEmpty()) {
                            // ZipCode id empty state
                            zipCodeEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Submit SignUp event
            is AddressUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    // TODO Trigger registration in authentication flow
                    Log.d("TAG", "onUiEvent: ${Gson().toJson(addressState.value)}")

                   /* makeSigUpRequest(
                        SignUpRequest(
                            username = addressState.value.username,
                            email = addressState.value.email,
                            password = addressState.value.password,
                            gender = addressState.value.gender,
                            phone = "8755092960",
                            userType = "provider",
                            latitude = _location.value.first.toString(),
                            longitude = _location.value.second.toString()
                        )
                    )*/
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
        val address = addressState.value.address.trim()
        val city = addressState.value.city.trim()
        val state = addressState.value.state.trim()
        val zipCode = addressState.value.zipCode.trim()


        // address empty
        if (address.isEmpty()) {
            addressState.value = addressState.value.copy(
                errorState = AddressErrorState(
                    addressErrorState = addressEmptyErrorState
                )
            )
            return false
        }

        // city empty
        if (city.isEmpty()) {
            addressState.value = addressState.value.copy(
                errorState = AddressErrorState(
                    cityErrorState = cityEmptyErrorState
                )
            )
            return false
        }

        //state Empty
        if (state.isEmpty()) {
            addressState.value = addressState.value.copy(
                errorState = AddressErrorState(
                    stateErrorState = stateEmptyErrorState
                )
            )
            return false
        }

        //zipCode Empty
        if (zipCode.isEmpty()) {
            addressState.value = addressState.value.copy(
                errorState = AddressErrorState(
                    zipCodeErrorState = zipCodeEmptyErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            addressState.value = addressState.value.copy(errorState = AddressErrorState())
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
                            addressState.value = addressState.value.copy(isSignUpSuccessful = true)
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