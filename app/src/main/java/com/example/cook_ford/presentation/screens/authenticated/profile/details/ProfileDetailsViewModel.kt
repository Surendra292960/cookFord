package com.example.cook_ford.presentation.screens.authenticated.profile.details
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.TimeSlots
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.profile.details.state.ProfileDetailState
import com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate.NoteErrorState
import com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate.NoteState
import com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate.NoteUiEvent
import com.example.cook_ford.presentation.screens.authenticated.profile.details.state.note_satate.noteEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in.state.ErrorState
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ProfileDetailsViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _timeSlots = mutableStateOf(TimeSlots())
    val timeSlots: State<TimeSlots> = _timeSlots

    private val _noteState = mutableStateOf(NoteState())
    val noteState: State<NoteState> = _noteState

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _profileState = mutableStateOf(ProfileDetailState())
    val profileState: State<ProfileDetailState> = _profileState

    init {
        getProfileId()?.let {
            Log.d("TAG", " stateHandle  : $it")
            getProfileId()?.let { makeProfileRequest(profileId = it) }
        }
    }

    fun getProfileId() = stateHandle.get<String>(AppConstants.PROFILE_ID)

    fun onUiEvent(noteUiEvent: NoteUiEvent) {
        when (noteUiEvent) {
            //Note changed
            is NoteUiEvent.NoteChanged -> {
                _noteState.value = _noteState.value.copy(
                    note = noteUiEvent.inputValue,
                    errorState = _noteState.value.errorState.copy(
                        noteErrorState = if (noteUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            noteEmptyErrorState
                    )
                )
            }

            NoteUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                Log.d("TAG", "onUiEvent: $inputsValidated")
                if (inputsValidated) {
                    // TODO Trigger login in authentication flow
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val note = _noteState.value.note.trim()

        // Review empty
        if (note.isEmpty()) {
            _noteState.value = _noteState.value.copy(
                errorState = NoteErrorState(
                    noteErrorState = noteEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            _noteState.value = _noteState.value.copy(errorState = NoteErrorState())
            return true
        }
    }


    fun getTimeSlots():List<TimeSlots>{
        val timeSlotsList:MutableList<TimeSlots> = mutableListOf()
        if (profileState.value.isSuccessful){
            profileState.value?.profile?.get(0)?.profile?.timeSlots?.let{
                it?.forEach { slots->
                    timeSlotsList.add(TimeSlots(slots.startTime?.trim().plus(" - "+slots.endTime?.trim())))
                    Log.d("TAG", "ProfileDetailScreen TimeSlots List : ${Gson().toJson(timeSlots)}")
                }
            }
        }
        return timeSlotsList
    }

    private fun makeProfileRequest(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequest profileId: $profileId")
        // _profileState.value = _profileState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _profileState.value = _profileState.value.copy(isLoading = false, profile = listOf(response), isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequest-> getProfileResponse: ${Gson().toJson(_profileState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequest-> Error: ${Gson().toJson(_profileState.value)}")
                    _profileState.value = _profileState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequest->: Loading")
                    _profileState.value = _profileState.value.copy(isLoading = true)
                }
            }
        }
    }
}
