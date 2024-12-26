package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_alternate_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_cityEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_jobTypeEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_nameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_profileImageEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.example.cook_ford.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditCookProfileViewModel  @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {

    val selectedItem = mutableSetOf<String>()
    var addCookProfileState = mutableStateOf(EditCookProfileState())
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
     * Function called on any event [EditCookProfileUiEvent]
     */
    fun onUiEvent(addCookProfileUiEvent: EditCookProfileUiEvent) {
        when (addCookProfileUiEvent) {

            //Profile Image changed
            is EditCookProfileUiEvent.ProfileImageChanged -> {
                Log.d("TAG", "onUiEvent: ${addCookProfileUiEvent.inputValue}")
                addCookProfileState.value = addCookProfileState.value.copy(
                    profileImage = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        profileImageErrorState = if (addCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_profileImageEmptyErrorState
                    )
                )
            }


            //JobType changed
            is EditCookProfileUiEvent.JobTypeChange -> {
                Log.d("TAG", "onUiEvent: ${addCookProfileUiEvent.inputValue}")
                addCookProfileState.value = addCookProfileState.value.copy(
                    jobType = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        jobTypeErrorState = if (addCookProfileUiEvent.inputValue.isNotEmpty())
                            ErrorState()
                        else
                            cook_jobTypeEmptyErrorState
                    )
                )
            }

            //CityChange changed
            is EditCookProfileUiEvent.CityChanged -> {
                addCookProfileState.value = addCookProfileState.value.copy(
                    city = addCookProfileUiEvent.inputValue,
                    errorState = addCookProfileState.value.errorState.copy(
                        cityErrorState = if (addCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_cityEmptyErrorState
                    )
                )
            }

            // UserName changed
            is EditCookProfileUiEvent.UserNameChanged -> {
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
            is EditCookProfileUiEvent.PhoneChanged -> {
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
            is EditCookProfileUiEvent.AlternatePhoneChanged -> {
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
            is EditCookProfileUiEvent.GenderChange -> {
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
            is EditCookProfileUiEvent.Submit -> {
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
     * Ideally it should be on domain layer (use case)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val userName = addCookProfileState.value.username.trim()
        val phone = addCookProfileState.value.phone.trim()
        val alternatePhone = addCookProfileState.value.alternatePhone.trim()
        val gender = addCookProfileState.value.gender.trim()
        val city = addCookProfileState.value.city.trim()
        val profileImage = addCookProfileState.value.profileImage.trim()
        val jobType = addCookProfileState.value.jobType


        //ProfileImage Not Selected
       /* if (profileImage.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = AddCookProfileErrorState(
                    profileImageErrorState = cook_profileImageEmptyErrorState
                )
            )
            return false
        }*/

        //JobType Not Selected
        if (jobType?.size == AppConstants.ZERO) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    jobTypeErrorState = cook_jobTypeEmptyErrorState
                )
            )
            return false
        }

        //City Not Selected
        if (city.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    cityErrorState = cook_cityEmptyErrorState
                )
            )
            return false
        }

        // userName empty
        if (userName.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    usernameErrorState = cook_nameEmptyErrorState
                )
            )
            return false
        }
        // Phone empty
        if (phone.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    phoneErrorState = cook_phoneEmptyErrorState
                )
            )
            return false
        }
        // Alternate Phone Empty
        if (alternatePhone.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    alternatePhoneErrorState = cook_alternate_phoneEmptyErrorState
                )
            )
            return false
        }

        //Gender Not Selected
        if (gender.isEmpty()) {
            addCookProfileState.value = addCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            addCookProfileState.value = addCookProfileState.value.copy(errorState = EditCookProfileErrorState())
            return true
        }
    }
}