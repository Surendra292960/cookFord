package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.authenticated_use_case.CuisineUseCase
import com.example.cook_ford.domain.use_cases.authenticated_use_case.EditCookProfileUseCase
import com.example.cook_ford.domain.use_cases.authenticated_use_case.LanguagesUseCase
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_addressEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_alternate_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_cityEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_cuisineEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_dobEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_experienceEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_jobTypeEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_languageEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_nameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_numberOfVisitEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_profileImageEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_religionEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_salaryEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_workAreaEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
class EditCookProfileViewModel  @Inject constructor(
    private val userSession: UserSession,
    private val languageUseCase: LanguagesUseCase,
    private val cuisineUseCase: CuisineUseCase,
    private val editCookProfileUseCase: EditCookProfileUseCase,
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val selectedItem = mutableSetOf<String>()
    val selectedCuisineItem = mutableSetOf<String>()
    val selectedLanguageItem = mutableSetOf<String>()

    private val _editCookProfileState: MutableStateFlow<EditCookProfileState>  = MutableStateFlow(EditCookProfileState())
    val editCookProfileState: StateFlow<EditCookProfileState> = _editCookProfileState

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

    init {
        userSession.getString(SessionConstant.USER_ID)?.let { getProfileRequestById(it) }
        getCuisinesRequest()
        getLanguagesRequest()
    }
    /**
     * Function called on any event [EditCookProfileUiEvent]
     */
    fun onUiEvent(editCookProfileUiEvent: EditCookProfileUiEvent) {
        when (editCookProfileUiEvent) {

            //Profile Image changed
            is EditCookProfileUiEvent.ProfileImageChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    profileImage = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        profileImageErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_profileImageEmptyErrorState
                    )
                )
            }


            //JobType changed
            is EditCookProfileUiEvent.JobTypeChange -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    jobType = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        jobTypeErrorState = if (editCookProfileUiEvent.inputValue.isNotEmpty())
                            ErrorState()
                        else
                            cook_jobTypeEmptyErrorState
                    )
                )
            }

            //Work Area changed
            is EditCookProfileUiEvent.WorkAreaChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    workArea = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        workAreaErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_workAreaEmptyErrorState
                    )
                )
            }


            //CityChange changed
            is EditCookProfileUiEvent.CityChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    city = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        cityErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_cityEmptyErrorState
                    )
                )
            }


            // UserName changed
            is EditCookProfileUiEvent.UserNameChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    username = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        usernameErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_nameEmptyErrorState
                    )
                )
            }
            // Phone changed
            is EditCookProfileUiEvent.PhoneChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    phone = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        phoneErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_phoneEmptyErrorState
                    )
                )
            }

            //Alternate Phone changed
            is EditCookProfileUiEvent.AlternatePhoneChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    alternatePhone = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        alternatePhoneErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_alternate_phoneEmptyErrorState
                    )
                )
            }

            //Dob changed
            is EditCookProfileUiEvent.DobChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value  = _editCookProfileState.value.copy(
                    dob = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        dobErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_dobEmptyErrorState
                    )
                )
            }

            //Address changed
            is EditCookProfileUiEvent.AddressChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    address = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        addressErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_addressEmptyErrorState
                    )
                )
            }

            //Experience changed
            is EditCookProfileUiEvent.ExperienceChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    experience = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        experienceErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_experienceEmptyErrorState
                    )
                )
            }

            //Salary changed
            is EditCookProfileUiEvent.SalaryChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    salary = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        salaryErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_salaryEmptyErrorState
                    )
                )
            }


            //Religion changed
            is EditCookProfileUiEvent.ReligionChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    religion = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        religionErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_religionEmptyErrorState
                    )
                )
            }

            is EditCookProfileUiEvent.CuisineChange -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    cuisine = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        cuisinesErrorState = if (editCookProfileUiEvent.inputValue.isNotEmpty())
                            ErrorState()
                        else
                            cook_cuisineEmptyErrorState
                    )
                )
            }

            is EditCookProfileUiEvent.LanguageChange ->{
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    languages = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        languagesErrorState = if (editCookProfileUiEvent.inputValue.isNotEmpty())
                            ErrorState()
                        else
                            cook_languageEmptyErrorState
                    )
                )
            }

            //No of Visit changed
            is EditCookProfileUiEvent.NumberOfVisitChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    numberOfVisit = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        numberOfVisitErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_numberOfVisitEmptyErrorState
                    )
                )
            }

            //Food Type changed
            is EditCookProfileUiEvent.FoodTypeChanged -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    foodType = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        numberOfVisitErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            cook_numberOfVisitEmptyErrorState
                    )
                )
            }


            //GenderChange changed
            is EditCookProfileUiEvent.GenderChange -> {
                Log.d("TAG", "onUiEvent: ${editCookProfileUiEvent.inputValue}")
                _editCookProfileState.value = _editCookProfileState.value.copy(
                    gender = editCookProfileUiEvent.inputValue,
                    errorState = _editCookProfileState.value.errorState.copy(
                        genderErrorState = if (editCookProfileUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            genderSelectionErrorState
                    )
                )
            }


            // Submit
            is EditCookProfileUiEvent.Submit -> {
                val inputsValidated = editCookProfileUseCase.invoke(_editCookProfileState)
                //Log.d("TAG", "onUiEvent: $inputsValidated")
                Log.d("TAG", "onUiEvent: ${Gson().toJson(_editCookProfileState.value)}")
                if (inputsValidated) {
                    // TODO Trigger Edit Profile in authentication flow
                    //makeSigInRequest(SignInRequest(email = editProfileState.value.email, password = editProfileState.value.username))
                }
            }
        }
    }



    private fun getCuisinesRequest() = viewModelScope.launch(IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        cuisineUseCase.invoke().collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        Log.d("TAG", "getCuisinesRequest getCuisines: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _editCookProfileState.emit(editCookProfileState.value.copy(cuisineResponse = response))
                            _editCookProfileState.value = _editCookProfileState.value.copy(isCuisineLoadedSuccessful = result.status)
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getCuisinesRequest: ${result.message}")
                    _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getCuisinesRequest: Loading")
                }
            }
        }
    }

    private fun getLanguagesRequest() = viewModelScope.launch(IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        languageUseCase.invoke().collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        Log.d("TAG", "getLanguagesRequest getLanguages: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _editCookProfileState.emit(editCookProfileState.value.copy(languagesResponse = response))
                            _editCookProfileState.value = _editCookProfileState.value.copy(isLanguageLoadedSuccessful = result.status)
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getLanguagesRequest: ${result.message}")
                    _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getLanguagesRequest: Loading")
                }
            }
        }
    }

    private fun getProfileRequestById(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "getProfileRequestById profileId: $profileId")
        // _accountState.value = _accountState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _editCookProfileState.value = _editCookProfileState.value.copy(
                                isLoading = false,
                                profileResponse = response,
                                username = response.username!!,
                                phone = response.phone!!,
                                isProfileLoadedSuccessful = true
                            )
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                        Log.d("TAG", "getProfileRequestById-> getProfileResponse: ${Gson().toJson(_editCookProfileState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getProfileRequestById-> Error: ${Gson().toJson(_editCookProfileState.value)}")
                    _editCookProfileState.value = _editCookProfileState.value.copy(errorMessage = result.message!!)
                    _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getProfileRequestById->: Loading")
                    _editCookProfileState.value = _editCookProfileState.value.copy(isLoading = true)
                }
            }
        }
    }
}