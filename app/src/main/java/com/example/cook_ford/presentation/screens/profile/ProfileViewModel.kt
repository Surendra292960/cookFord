package com.example.cook_ford.presentation.screens.profile
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.response.ProfileResponse
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.presentation.common.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.profile.state.ProfileState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase, private val userSession: UserSession) : ViewModel() {
    var profileState = mutableStateOf(ProfileState())
        private set

    private val _profileResponse: MutableStateFlow<List<ProfileResponse>> = MutableStateFlow(emptyList())
    val profileResponse: StateFlow<List<ProfileResponse>> = _profileResponse.asStateFlow()

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private val _onProcessSuccess = MutableSharedFlow<String>()
    val onProcessSuccess = _onProcessSuccess.asSharedFlow()


    fun getProfileRequest() = viewModelScope.launch(Dispatchers.IO) {
        _viewState.update { currentState -> currentState.copy(isLoading = true) }
        profileUseCase.invoke().collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        Log.d("TAG", "getProfileRequest getProfileResponse: ${Gson().toJson(result)}")
                        result.data?.let { response->
                            _profileResponse.emit(response)
                            profileState.value = profileState.value.copy(isSuccessful = result.status)
                            //TODO save token after dialog dismiss
                            _viewState.update { currentState -> currentState.copy(isLoading = false) }
                        }
                        Log.d("TAG", "getProfileRequest: ${userSession.getString(SessionConstant.ACCESS_TOKEN)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getProfileRequest: ${result.message}")
                    _viewState.update { currentState -> currentState.copy(isLoading = false) }
                    _onProcessSuccess.emit(result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getProfileRequest: Loading")
                }
            }
        }
    }
}