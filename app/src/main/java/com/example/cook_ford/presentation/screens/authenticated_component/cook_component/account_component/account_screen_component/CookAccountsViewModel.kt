package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.domain.use_cases.authenticated_use_case.ProfileUseCase
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.state.CookAccountState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.state.CookReviewErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.state.CookReviewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.state.CookReviewUiEvent
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.state.note_satate.ratingEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.state.note_satate.reviewEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.state.ErrorState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import javax.inject.Inject

@HiltViewModel
class CookAccountsViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val userSession: UserSession,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    val isTrue  =  mutableStateOf(false)
    var job: Job?=null
    fun main() {        // Launch a coroutine job
        job = viewModelScope.launch {
            var counter = 0
            while (isActive) { // Check if the coroutine is still active
                println("Working... Counter: $counter")
                counter++
                if (isTrue.value){
                    cancel()
                    yield()
                    delay(500) // Simulate some work
                }
            }
            println("Coroutine is canceled")
        }
    }

    private val _accountState = mutableStateOf(CookAccountState())
    val accountState: State<CookAccountState> = _accountState

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

    fun onReViewUiEvent(cookReviewUiEvent: CookReviewUiEvent) {
        when (cookReviewUiEvent) {
            //Rating changed
            is CookReviewUiEvent.RatingChanged -> {
                reviewState.value = reviewState.value.copy(
                    rating = cookReviewUiEvent.inputValue,
                    errorState = reviewState.value.errorState.copy(
                        reviewErrorState = if (cookReviewUiEvent.inputValue != 0.0f)
                            ErrorState()
                        else
                            ratingEmptyErrorState
                    )
                )
            }
            //Review changed
            is CookReviewUiEvent.ReViewChanged -> {
                reviewState.value = reviewState.value.copy(
                    review = cookReviewUiEvent.inputValue,
                    errorState = reviewState.value.errorState.copy(
                        reviewErrorState = if (cookReviewUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            reviewEmptyErrorState
                    )
                )
            }

            CookReviewUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    reviewState.value = reviewState.value.copy(isSuccessful = true)
                    //reviewState.value = ReviewState()
                    Log.d("TAG", "onReViewUiEvent: ${Gson().toJson(reviewState.value)}")
                    // TODO Trigger login in authentication flow
                }
            }
        }
    }


    private fun validateInputs(): Boolean {
        val review = reviewState.value.review.trim()

        // Review empty
        if (review.isEmpty()) {
            reviewState.value = reviewState.value.copy(
                errorState = CookReviewErrorState(
                    reviewErrorState = reviewEmptyErrorState
                )
            )
            return false
        }
        // No errors
        else {
            // Set default error state
            reviewState.value = reviewState.value.copy(errorState = CookReviewErrorState())
            return true
        }
    }

   /* private fun makeProfileRequest(profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequest profileId: $profileId")
        // _accountState.value = _accountState.value.copy(isLoading = true)
        profileUseCase.invoke(profileId).collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _accountState.value = _accountState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequest-> getProfileResponse: ${Gson().toJson(_accountState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequest-> Error: ${Gson().toJson(_accountState.value)}")
                    _accountState.value = _accountState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequest->: Loading")
                    _accountState.value = _accountState.value.copy(isLoading = true)
                }
            }
        }
    }*/

    private fun getProfileRequestById(authToken:String, profileId: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "makeProfileRequest profileId: $profileId")
        // _accountState.value = _accountState.value.copy(isLoading = true)
        profileUseCase.invoke(authToken, profileId,"").collect { result ->
            when(result){
                is NetworkResult.Success->{
                    if (result.status == true){
                        result.data?.let { response->
                            _accountState.value = _accountState.value.copy(isLoading = false, profileResponse = response, isSuccessful = true)
                        }
                        Log.d("TAG", "makeProfileRequest-> getProfileResponse: ${Gson().toJson(_accountState.value)}")
                    }
                }
                is NetworkResult.Error->{
                    Log.d("TAG", "makeProfileRequest-> Error: ${Gson().toJson(_accountState.value)}")
                    _accountState.value = _accountState.value.copy(errorMessage = result.message!!)
                }
                is NetworkResult.Loading->{
                    Log.d("TAG", "makeProfileRequest->: Loading")
                    _accountState.value = _accountState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun getUserType():String?{
        return userSession.getString(SessionConstant.USER_TYPE)
    }
}