package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.list_screen_component
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
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.list_screen_component.state.CookProfileState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CookProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession) : ViewModel() {

    var shareProfile by mutableStateOf(ProfileResponse())

    private val _profileState = mutableStateOf(CookProfileState())
    val profileState: State<CookProfileState> = _profileState

    init {
        userSession.getString(SessionConstant.USER_ID)?.let { getProfileRequestById(it) }
    }

    private fun getProfileRequestById(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        _profileState.value = _profileState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _profileState.value = _profileState.value.copy(isLoading = false, profile = listOf(response), isSuccessful = true)
                        }
                        Log.d("TAG", "getProfileRequestById getProfileResponse: ${Gson().toJson(_profileState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getProfileRequestById: ${result.message}")
                    _profileState.value = _profileState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getProfileRequestById: Loading")
                    _profileState.value = _profileState.value.copy(isLoading = true)

                }
            }
        }
    }

    /*This function used to get USER TYPE from shared preferences*/
    fun getUserType():String?{
        return userSession.getString(SessionConstant.USER_TYPE)
    }
}