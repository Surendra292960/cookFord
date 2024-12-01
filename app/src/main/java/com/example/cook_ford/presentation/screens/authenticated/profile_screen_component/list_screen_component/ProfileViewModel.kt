package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.list_screen_component
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.list_screen_component.state.ProfileState
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession) : ViewModel() {

    var shareProfile by mutableStateOf(ProfileResponse())

    private val _profileState = mutableStateOf(ProfileState())
    val profileState: State<ProfileState> = _profileState

    fun getProfileRequest() = viewModelScope.launch(Dispatchers.IO) {
        _profileState.value = _profileState.value.copy(isLoading = true)
        profileUseCase.invoke().collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _profileState.value = _profileState.value.copy(isLoading = false, profile = result.data, isSuccessful = true)
                        }
                        Log.d("TAG", "getProfileRequest getProfileResponse: ${Gson().toJson(_profileState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getProfileRequest: ${result.message}")
                    _profileState.value = _profileState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getProfileRequest: Loading")
                    _profileState.value = _profileState.value.copy(isLoading = true)

                }
            }
        }
    }

    fun getResponseFromPref():String?{
        return userSession.getString(SessionConstant.USER_TYPE)
    }
}