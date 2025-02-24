package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_account

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.state.CookReviewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_account.state.ManageAccountState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageCookAccountViewModel @Inject constructor(
    private val userSession: UserSession,
    private val profileUseCase: ProfileUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _manageAccountState = mutableStateOf(ManageAccountState())
    val manageAccountState: State<ManageAccountState> = _manageAccountState

    val reviewState = mutableStateOf(CookReviewState())

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    init {
        userSession.apply {
            getString(SessionConstant.ACCESS_TOKEN)?.let { token ->
                getString(SessionConstant.USER_ID)?.let { id ->
                    getProfileRequestById(
                        token,
                        id
                    )
                }
            }
        }
    }

    fun setViewState(viewState: MainViewState) {
        _viewState.value = viewState
    }


    private fun getProfileRequestById(authToken:String, profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "getProfileRequestById profileId: $profileId")
        // _manageAccountState.value = _manageAccountState.value.copy(isLoading = true)
        profileUseCase.invoke(authToken, profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _manageAccountState.value = _manageAccountState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "getProfileRequestById-> getProfileResponse: ${Gson().toJson(_manageAccountState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "getProfileRequestById-> Error: ${Gson().toJson(_manageAccountState.value)}")
                    _manageAccountState.value = _manageAccountState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "getProfileRequestById->: Loading")
                    _manageAccountState.value = _manageAccountState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun getUserType():String?{
        return userSession.getString(SessionConstant.USER_TYPE)
    }
}