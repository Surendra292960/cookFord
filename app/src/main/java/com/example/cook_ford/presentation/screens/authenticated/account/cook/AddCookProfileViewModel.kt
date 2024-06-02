package com.example.cook_ford.presentation.screens.authenticated.account.cook
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.profile_response.TimeSlots
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileErrorState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileUiEvent
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.cook_alternate_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.cook_nameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.cook_phoneEmptyErrorState
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
class AddCookProfileViewModel  @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    val selectedItem: MutableList<String> = mutableListOf()
    var addCookProfileState = mutableStateOf(AddCookProfileState())
        private set

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
     * Function called on any event [AddCookProfileUiEvent]
     */
    fun onUiEvent(addCookProfileUiEvent: AddCookProfileUiEvent) {
        when (addCookProfileUiEvent) {

            // UserName changed
            is AddCookProfileUiEvent.UserNameChanged -> {
                addCookProfileState.value = addCookProfileState.value.copy(
                    username = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        usernameErrorState = if (addCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_nameEmptyErrorState
                    )
                )
            }
            // Email changed
            is AddCookProfileUiEvent.PhoneChanged -> {
                addCookProfileState.value = addCookProfileState.value.copy(
                    phone = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        phoneErrorState = if (addCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_phoneEmptyErrorState
                    )
                )
            }

            //Mobile changed
            is AddCookProfileUiEvent.AlternatePhoneChanged -> {
                Log.d("TAG", "onUiEvent: ${addCookProfileUiEvent.inputValue}")
                addCookProfileState.value = addCookProfileState.value.copy(
                    alternatePhone = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        alternatePhoneErrorState = if (addCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_alternate_phoneEmptyErrorState
                    )
                )
            }

            //GenderChange changed
            is AddCookProfileUiEvent.GenderChange -> {
                addCookProfileState.value = addCookProfileState.value.copy(
                    gender = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        genderErrorState = if (addCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            genderSelectionErrorState
                    )
                )
            }

            // Submit
            is AddCookProfileUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                //Log.d("TAG", "onUiEvent: $inputsValidated")
                Log.d("TAG", "onUiEvent: ${addCookProfileState.value}")
                if (inputsValidated) {
                    // TODO Trigger Edit Profile in authentication flow

                    //makeSigInRequest(SignInRequest(email = editProfileState.value.email, password = editProfileState.value.username))
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
        val userName = addCookProfileState.value.username.trim()
        val phone = addCookProfileState.value.phone.trim()
        val alternatePhone = addCookProfileState.value.alternatePhone.trim()
        val gender = addCookProfileState.value.gender.trim()

        // userName empty
        if (userName.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = AddCookProfileErrorState(
                    usernameErrorState = cook_nameEmptyErrorState
                )
            )
            return false
        }
        // Phone empty
        if (phone.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = AddCookProfileErrorState(
                    phoneErrorState = cook_phoneEmptyErrorState
                )
            )
            return false
        }
        // Alternate Phone Empty
        if (alternatePhone.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = AddCookProfileErrorState(
                    alternatePhoneErrorState = cook_alternate_phoneEmptyErrorState
                )
            )
            return false
        }

        //Gender Not Selected
        if (gender.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = AddCookProfileErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            addCookProfileState.value = addCookProfileState.value.copy(errorState = AddCookProfileErrorState())
            return true
        }
    }
}