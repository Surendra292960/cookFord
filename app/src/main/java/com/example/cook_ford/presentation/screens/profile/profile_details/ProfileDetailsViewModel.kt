package com.example.cook_ford.presentation.screens.profile.profile_details
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.screens.profile.profile_details.state.ProfileDetailState
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _profileState = mutableStateOf(ProfileDetailState())
    val profileState: State<ProfileDetailState> = _profileState

    init {
        val profileId = stateHandle.get<String>(AppConstants.PROFILE_ID)
        Log.d("TAG", " stateHandle  : $profileId: ")
        if (!profileId.isNullOrEmpty()){
            makeProfileRequest(profileId = profileId)
        }
    }

    private fun makeProfileRequest(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
      //  _profileState.value = _profileState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _profileState.value = _profileState.value.copy(isLoading = false, profile = listOf(result.data), isSuccessful = true)
                        }
                        Log.d("TAG", "getProfileById getProfileResponse: ${Gson().toJson(_profileState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getProfileById Error: ${result.message}")
                    _profileState.value = _profileState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getProfileById: Loading")
                    _profileState.value = _profileState.value.copy(isLoading = true)
                }
            }
        }
    }
}