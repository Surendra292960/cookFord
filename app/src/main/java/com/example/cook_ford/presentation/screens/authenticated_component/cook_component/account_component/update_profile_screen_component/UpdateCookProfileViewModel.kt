package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.update_profile_screen_component
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.SessionConstant.USER_TYPE
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.add_cook_screen_component.state.cook_profileImageEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.update_profile_screen_component.state.UpdateCookProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.update_profile_screen_component.state.UpdateCookProfileUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.update_profile_screen_component.state.UpdateCookProfileErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.usernameEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UpdateCookProfileViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    private val _updateCookProfileState = mutableStateOf(UpdateCookProfileState())
    val updateCookProfileState:State<UpdateCookProfileState> = _updateCookProfileState

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
     * Function called on any login event [UpdateCookProfileUiEvent]
     */
    fun onUiEvent(updateCookProfileUiEvent: UpdateCookProfileUiEvent) {
        when (updateCookProfileUiEvent) {

            // Profile Image changed
            is UpdateCookProfileUiEvent.ProfileImageChanged -> {
                _updateCookProfileState.value = _updateCookProfileState.value.copy(
                    profileImage = updateCookProfileUiEvent.inputValue,
                    errorState = _updateCookProfileState.value.errorState.copy(
                        profileImageErrorState = if (updateCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_profileImageEmptyErrorState
                    )
                )
            }

            // UserName changed
            is UpdateCookProfileUiEvent.UserNameChanged -> {
                Log.d("TAG", "onUiEvent: ${updateCookProfileUiEvent.inputValue}")
                _updateCookProfileState.value = _updateCookProfileState.value.copy(
                    username = updateCookProfileUiEvent.inputValue,
                    errorState = _updateCookProfileState.value.errorState.copy(
                        usernameErrorState = if (updateCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }
            // Email changed
            is UpdateCookProfileUiEvent.EmailChanged -> {
                _updateCookProfileState.value = _updateCookProfileState.value.copy(email = updateCookProfileUiEvent.inputValue)
                Log.d("TAG", "onUiEvent: ${updateCookProfileState.value.email}")

                _updateCookProfileState.value = _updateCookProfileState.value.copy(
                    email = updateCookProfileUiEvent.inputValue,
                    errorState = _updateCookProfileState.value.errorState.copy(
                        emailErrorState = if (updateCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            //Mobile changed
            is UpdateCookProfileUiEvent.PhoneChanged -> {
                Log.d("TAG", "onUiEvent: ${updateCookProfileUiEvent.inputValue}")
                _updateCookProfileState.value = _updateCookProfileState.value.copy(
                    phone = updateCookProfileUiEvent.inputValue,
                    errorState = _updateCookProfileState.value.errorState.copy(
                        phoneErrorState = if (updateCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            phoneEmptyErrorState
                    )
                )
            }

            //GenderChange changed
            is UpdateCookProfileUiEvent.GenderChange -> {
                _updateCookProfileState.value = _updateCookProfileState.value.copy(
                    gender = updateCookProfileUiEvent.inputValue,
                    errorState = _updateCookProfileState.value.errorState.copy(
                        phoneErrorState = if (updateCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            genderSelectionErrorState
                    )
                )
            }

            // Submit
            is UpdateCookProfileUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                //Log.d("TAG", "onUiEvent: $inputsValidated")
               // Log.d("TAG", "onUiEvent: ${_editProfileState.value}")
                if (inputsValidated) {
                    Log.d("TAG", "onUiEvent: ${_updateCookProfileState.value}")
                    // TODO Trigger Edit Profile in authentication flow

                    //makeSigInRequest(SignInRequest(email = _editProfileState.value.email, password = _editProfileState.value.username))
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
        val userName = updateCookProfileState.value.username.trim()
        val email = updateCookProfileState.value.email.trim()
        val phone = updateCookProfileState.value.phone.trim()
        val gender = updateCookProfileState.value.gender.trim()

        // userName empty
        if (userName.isEmpty()) {
            _updateCookProfileState.value = _updateCookProfileState.value.copy(
                errorState = UpdateCookProfileErrorState(
                    usernameErrorState = usernameEmptyErrorState
                )
            )
            return false
        }
        // Email empty
        if (email.isEmpty()) {
            _updateCookProfileState.value = _updateCookProfileState.value.copy(
                errorState = UpdateCookProfileErrorState(
                    emailErrorState = emailEmptyErrorState
                )
            )
            return false
        }
        // Email Matcher
        if (email.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _updateCookProfileState.value = _updateCookProfileState.value.copy(
                    errorState = UpdateCookProfileErrorState(
                        emailErrorState = invalidUserNameErrorState
                    )
                )
                return false
            }
        }

        //Phone Empty
        if (phone.isEmpty()) {
            _updateCookProfileState.value = _updateCookProfileState.value.copy(
                errorState = UpdateCookProfileErrorState(
                    phoneErrorState = phoneEmptyErrorState
                )
            )
            return false
        }

        //Gender Not Selected
        if (gender.isEmpty()) {
            _updateCookProfileState.value = _updateCookProfileState.value.copy(
                errorState = UpdateCookProfileErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            _updateCookProfileState.value = _updateCookProfileState.value.copy(errorState = UpdateCookProfileErrorState())
            return true
        }
    }

    fun setProfileData(profileResponse: ProfileResponse?) {
        _updateCookProfileState.value = _updateCookProfileState.value.copy(
            isLoading = false,
            profileResponse = profileResponse,
            isSuccessful = true,
            username = profileResponse?.username.toString(),
            email = profileResponse?.email.toString(),
            phone = profileResponse?.phone.toString(),
            gender = profileResponse?.gender.toString()
        )
    }

    fun getUserType():String?{
        return userSession.getString(USER_TYPE)
    }

    fun signOut(){
        return userSession.clear()
    }
}