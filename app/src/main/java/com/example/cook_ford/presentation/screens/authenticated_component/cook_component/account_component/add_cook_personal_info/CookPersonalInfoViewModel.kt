package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info

import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant.AUTH_ID
import com.example.cook_ford.data.local.SessionConstant.USER_TYPE
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.use_cases.SignUpUseCase
import com.example.cook_ford.presentation.component.widgets.dialog.DialogState
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.CookPersonalInfoErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.CookPersonalInfoState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.CookPersonalInfoUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.addressEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.cityEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.firstNameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.lastNameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.religionEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.stateEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.zipCodeEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
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
class CookPersonalInfoViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase, private val userSession: UserSession) :ViewModel() {

    var dialogState = mutableStateOf(DialogState())
        private set

    private val _cookPersonalInfoState = mutableStateOf(CookPersonalInfoState())
    val cookPersonalInfoState:State<CookPersonalInfoState> = _cookPersonalInfoState

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
     * Function called on any CookPersonalInfo event [CookPersonalInfoUiEvent]
     */
    fun onUiEvent(cookPersonalInfoUiEvent: CookPersonalInfoUiEvent) {
        when (cookPersonalInfoUiEvent) {

            // First name id changed event
            is CookPersonalInfoUiEvent.FirstNameChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    firstName = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        firstNameErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
                            // first name empty state
                            firstNameEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Last name id changed event
            is CookPersonalInfoUiEvent.LastNameChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    lastName = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        lastNameErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
                            //Last name empty state
                            lastNameEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Address id changed event
            is CookPersonalInfoUiEvent.AddressChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    address = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        addressErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
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
            is CookPersonalInfoUiEvent.CityChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    city = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        cityErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
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
            is CookPersonalInfoUiEvent.StateChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    state = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        stateErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
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
            is CookPersonalInfoUiEvent.ZipCodeChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    zipCode = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        zipCodeErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
                            // ZipCode id empty state
                            zipCodeEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Religion id changed event
            is CookPersonalInfoUiEvent.ReligionChange -> {
                _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                    religion = cookPersonalInfoUiEvent.inputValue,
                    errorState = _cookPersonalInfoState.value.errorState.copy(
                        religionErrorState = if (cookPersonalInfoUiEvent.inputValue.trim().isEmpty()) {
                            // Religion id empty state
                            religionEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }
                    )
                )
            }

            // Submit SignUp event
            is CookPersonalInfoUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    _viewState.update { currentState -> currentState.copy(isLoading = true) }
                    // TODO Trigger registration in authentication flow
                    Log.d("TAG", "onUiEvent: ${Gson().toJson(_cookPersonalInfoState.value)}")

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
     * Ideally it should be on domain layer (useCase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val firstName = cookPersonalInfoState.value.firstName.trim()
        val lastName = cookPersonalInfoState.value.lastName.trim()
        val address = cookPersonalInfoState.value.address.trim()
        val city = cookPersonalInfoState.value.city.trim()
        val state = cookPersonalInfoState.value.state.trim()
        val zipCode = cookPersonalInfoState.value.zipCode.trim()
        val religion = cookPersonalInfoState.value.religion.trim()


        //religion Empty
        if (religion.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    religionErrorState = religionEmptyErrorState
                )
            )
            return false
        }

        // firstName empty
        if (firstName.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    firstNameErrorState = firstNameEmptyErrorState
                )
            )
            return false
        }

        // lastName empty
        if (lastName.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    lastNameErrorState = lastNameEmptyErrorState
                )
            )
            return false
        }

        // address empty
        if (address.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    addressErrorState = addressEmptyErrorState
                )
            )
            return false
        }

        // city empty
        if (city.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    cityErrorState = cityEmptyErrorState
                )
            )
            return false
        }

        //state Empty
        if (state.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    stateErrorState = stateEmptyErrorState
                )
            )
            return false
        }

        //zipCode Empty
        if (zipCode.isEmpty()) {
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
                errorState = CookPersonalInfoErrorState(
                    zipCodeErrorState = zipCodeEmptyErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(errorState = CookPersonalInfoErrorState())
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
                          //  _cookPersonalInfoState.emit(result.data)
                            _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(isSuccessful = true)
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

    fun setProfileData(profileResponse: ProfileResponse?) {
        _cookPersonalInfoState.value = _cookPersonalInfoState.value.copy(
            firstName = profileResponse?.username.toString(),
            lastName = profileResponse?.username.toString(),
            address = "",
            city = "",
            state = "",
            zipCode = "",
            isLoading = false,
            profileResponse = profileResponse,
            isSuccessful = false,
        )
    }

    fun getUserType():String?{
        return userSession.getString(USER_TYPE)
    }
}