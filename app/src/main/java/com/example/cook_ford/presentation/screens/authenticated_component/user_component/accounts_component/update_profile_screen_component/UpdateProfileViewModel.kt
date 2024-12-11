package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component

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
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.usernameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component.state.UpdateProfileErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component.state.UpdateProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component.state.UpdateProfileUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    private val _updateProfileState = mutableStateOf(UpdateProfileState())
    val updateProfileState:State<UpdateProfileState> = _updateProfileState

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
     * Function called on any login event [UpdateProfileUiEvent]
     */
    fun onUiEvent(updateProfileUiEvent: UpdateProfileUiEvent) {
        when (updateProfileUiEvent) {

            // Profile Image changed
            is UpdateProfileUiEvent.ProfileImageChanged -> {
                _updateProfileState.value = _updateProfileState.value.copy(
                    profileImage = updateProfileUiEvent.inputValue,
                    errorState = _updateProfileState.value.errorState.copy(
                        profileImageErrorState = if (updateProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_profileImageEmptyErrorState
                    )
                )
            }

            // UserName changed
            is UpdateProfileUiEvent.UserNameChanged -> {
                Log.d("TAG", "onUiEvent: ${updateProfileUiEvent.inputValue}")
                _updateProfileState.value = _updateProfileState.value.copy(
                    username = updateProfileUiEvent.inputValue,
                    errorState = _updateProfileState.value.errorState.copy(
                        usernameErrorState = if (updateProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }
            // Email changed
            is UpdateProfileUiEvent.EmailChanged -> {
                _updateProfileState.value = _updateProfileState.value.copy(email = updateProfileUiEvent.inputValue)
                Log.d("TAG", "onUiEvent: ${updateProfileState.value.email}")

                _updateProfileState.value = _updateProfileState.value.copy(
                    email = updateProfileUiEvent.inputValue,
                    errorState = _updateProfileState.value.errorState.copy(
                        emailErrorState = if (updateProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            //Mobile changed
            is UpdateProfileUiEvent.PhoneChanged -> {
                Log.d("TAG", "onUiEvent: ${updateProfileUiEvent.inputValue}")
                _updateProfileState.value = _updateProfileState.value.copy(
                    phone = updateProfileUiEvent.inputValue,
                    errorState = _updateProfileState.value.errorState.copy(
                        phoneErrorState = if (updateProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            phoneEmptyErrorState
                    )
                )
            }

            //GenderChange changed
            is UpdateProfileUiEvent.GenderChange -> {
                _updateProfileState.value = _updateProfileState.value.copy(
                    gender = updateProfileUiEvent.inputValue,
                    errorState = _updateProfileState.value.errorState.copy(
                        phoneErrorState = if (updateProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            genderSelectionErrorState
                    )
                )
            }

            // Submit
            is UpdateProfileUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                //Log.d("TAG", "onUiEvent: $inputsValidated")
               // Log.d("TAG", "onUiEvent: ${_editProfileState.value}")
                if (inputsValidated) {
                    Log.d("TAG", "onUiEvent: ${_updateProfileState.value}")
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
        val userName = updateProfileState.value.username.trim()
        val email = updateProfileState.value.email.trim()
        val phone = updateProfileState.value.phone.trim()
        val gender = updateProfileState.value.gender.trim()

        // userName empty
        if (userName.isEmpty()) {
            _updateProfileState.value = _updateProfileState.value.copy(
                errorState = UpdateProfileErrorState(
                    usernameErrorState = usernameEmptyErrorState
                )
            )
            return false
        }
        // Email empty
        if (email.isEmpty()) {
            _updateProfileState.value = _updateProfileState.value.copy(
                errorState = UpdateProfileErrorState(
                    emailErrorState = emailEmptyErrorState
                )
            )
            return false
        }
        // Email Matcher
        if (email.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _updateProfileState.value = _updateProfileState.value.copy(
                    errorState = UpdateProfileErrorState(
                        emailErrorState = invalidUserNameErrorState
                    )
                )
                return false
            }
        }

        //Phone Empty
        if (phone.isEmpty()) {
            _updateProfileState.value = _updateProfileState.value.copy(
                errorState = UpdateProfileErrorState(
                    phoneErrorState = phoneEmptyErrorState
                )
            )
            return false
        }

        //Gender Not Selected
        if (gender.isEmpty()) {
            _updateProfileState.value = _updateProfileState.value.copy(
                errorState = UpdateProfileErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            _updateProfileState.value = _updateProfileState.value.copy(errorState = UpdateProfileErrorState())
            return true
        }
    }

    fun setProfileData(profileResponse: ProfileResponse?) {
        _updateProfileState.value = _updateProfileState.value.copy(
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