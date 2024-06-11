package com.example.cook_ford.presentation.screens.authenticated.accounts.profile

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.accounts.cook.state.cook_profileImageEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated.accounts.profile.state.EditProfileErrorState
import com.example.cook_ford.presentation.screens.authenticated.accounts.profile.state.EditProfileState
import com.example.cook_ford.presentation.screens.authenticated.accounts.profile.state.EditProfileUiEvent
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.genderSelectionErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.passwordEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up.state.emailEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up.state.invalidUserNameErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up.state.usernameEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    private val _editProfileState = mutableStateOf(EditProfileState())
    val editProfileState:State<EditProfileState> = _editProfileState

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
     * Function called on any login event [EditProfileUiEvent]
     */
    fun onUiEvent(editProfileUiEvent: EditProfileUiEvent) {
        when (editProfileUiEvent) {

            // Profile Image changed
            is EditProfileUiEvent.ProfileImageChanged -> {
                _editProfileState.value = _editProfileState.value.copy(
                    profileImage = editProfileUiEvent.inputValue,
                    errorState = _editProfileState.value.errorState.copy(
                        profileImageErrorState = if (editProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_profileImageEmptyErrorState
                    )
                )
            }

            // UserName changed
            is EditProfileUiEvent.UserNameChanged -> {
                Log.d("TAG", "onUiEvent: ${editProfileUiEvent.inputValue}")
                _editProfileState.value = _editProfileState.value.copy(
                    username = editProfileUiEvent.inputValue,
                    errorState = _editProfileState.value.errorState.copy(
                        usernameErrorState = if (editProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }
            // Email changed
            is EditProfileUiEvent.EmailChanged -> {
                Log.d("TAG", "onUiEvent: ${editProfileUiEvent.inputValue}")
                _editProfileState.value = _editProfileState.value.copy(
                    email = editProfileUiEvent.inputValue,
                    errorState = _editProfileState.value.errorState.copy(
                        emailErrorState = if (editProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            //Mobile changed
            is EditProfileUiEvent.PhoneChanged -> {
                Log.d("TAG", "onUiEvent: ${editProfileUiEvent.inputValue}")
                _editProfileState.value = _editProfileState.value.copy(
                    phone = editProfileUiEvent.inputValue,
                    errorState = _editProfileState.value.errorState.copy(
                        phoneErrorState = if (editProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            phoneEmptyErrorState
                    )
                )
            }

            //GenderChange changed
            is EditProfileUiEvent.GenderChange -> {
                _editProfileState.value = _editProfileState.value.copy(
                    gender = editProfileUiEvent.inputValue,
                    errorState = _editProfileState.value.errorState.copy(
                        phoneErrorState = if (editProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            genderSelectionErrorState
                    )
                )
            }

            // Submit
            is EditProfileUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                //Log.d("TAG", "onUiEvent: $inputsValidated")
               // Log.d("TAG", "onUiEvent: ${_editProfileState.value}")
                if (inputsValidated) {
                    Log.d("TAG", "onUiEvent: ${_editProfileState.value}")
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
        val userName = editProfileState.value.username.trim()
        val email = editProfileState.value.email.trim()
        val phone = editProfileState.value.phone.trim()
        val gender = editProfileState.value.gender.trim()

        // userName empty
        if (userName.isEmpty()) {
            _editProfileState.value = _editProfileState.value.copy(
                errorState = EditProfileErrorState(
                    usernameErrorState = usernameEmptyErrorState
                )
            )
            return false
        }
        // Email empty
        if (email.isEmpty()) {
            _editProfileState.value = _editProfileState.value.copy(
                errorState = EditProfileErrorState(
                    emailErrorState = emailEmptyErrorState
                )
            )
            return false
        }
        // Email Matcher
        if (email.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _editProfileState.value = _editProfileState.value.copy(
                    errorState = EditProfileErrorState(
                        emailErrorState = invalidUserNameErrorState
                    )
                )
                return false
            }
        }

        //Phone Empty
        if (phone.isEmpty()) {
            _editProfileState.value = _editProfileState.value.copy(
                errorState = EditProfileErrorState(
                    phoneErrorState = phoneEmptyErrorState
                )
            )
            return false
        }

        //Gender Not Selected
        if (gender.isEmpty()) {
            _editProfileState.value = _editProfileState.value.copy(
                errorState = EditProfileErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            _editProfileState.value = _editProfileState.value.copy(errorState = EditProfileErrorState())
            return true
        }
    }

    fun setProfileData(profileResponse: ProfileResponse?) {
        _editProfileState.value = _editProfileState.value.copy(isLoading = false, profileResponse = profileResponse, isSuccessful = true)
    }
}