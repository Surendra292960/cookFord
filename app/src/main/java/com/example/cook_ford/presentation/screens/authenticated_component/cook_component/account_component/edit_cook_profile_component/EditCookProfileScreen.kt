package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileUiEvent
import com.example.cook_ford.presentation.theme.DeepGreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun EditCookProfileScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val editCookProfileViewModel: EditCookProfileViewModel = hiltViewModel()
    val addCookProfileState by editCookProfileViewModel.editCookProfileState.collectAsState()
    val showDialogState: Boolean by editCookProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val changeProfileState = remember { mutableStateOf("") }
    val viewState: MainViewState by editCookProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle


    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    if (addCookProfileState.isCookEditSuccessful) {

        ShowCustomDialog("message", editCookProfileViewModel, showDialogState)

        Log.d("TAG", "AddCookProfileScreen: $showDialogState")
        /**
         * Navigate to Authenticated navigation route
         * once signIn is successful
         */
        if (!showDialogState) {
            LaunchedEffect(key1 = true) {
                onNavigateToAuthenticatedRoute.invoke()
            }
        }
    } else {

        // Full Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .background(Color.White)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally) {

            // EditProfile Inputs Composable
            CookProfileForm(
                changeProfileState = changeProfileState,
                editCookProfileState = addCookProfileState,
                editCookProfileViewModel = editCookProfileViewModel,
                viewState = viewState,
                changeProfileImageState = {
                    changeProfileState.value = it
                    Log.d("TAG", "AddCookProfileScreen: ${changeProfileState.value}")
                })
            // Show Snackbar
            ShowSnackbar(editCookProfileViewModel, lifecycle, snackBarHostState)
        }
    }
}

@Composable
fun CookProfileForm(
    changeProfileState:MutableState<String>,
    editCookProfileState: EditCookProfileState,
    editCookProfileViewModel: EditCookProfileViewModel,
    viewState: MainViewState,
    changeProfileImageState: (String) -> Unit) {

    // EditProfile Inputs Composable
    EditCookProfileForm(
        changeProfileState = changeProfileState,
        editCookProfileState = editCookProfileState,
        viewState = viewState,


        onProfileImageChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.ProfileImageChanged(
                    inputString
                )
            )
        },

        onJobTypeChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.JobTypeChange(
                    inputString
                )
            )
        },

        onWorkAreaChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.WorkAreaChanged(
                    inputString
                )
            )
        },

        onUserNameChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.UserNameChanged(
                    inputString
                )
            )
        },

        onDobChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.DobChanged(
                    inputString
                )
            )
        },

        onAddressChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.AddressChanged(
                    inputString
                )
            )
        },

        onReligionChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.ReligionChanged(
                    inputString
                )
            )
        },

        onExperienceChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.ExperienceChanged(
                    inputString
                )
            )
        },

        onSalaryChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.SalaryChanged(
                    inputString
                )
            )
        },

        onNumberOfVisitChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.NumberOfVisitChanged(
                    inputString
                )
            )
        },

        onFoodTypeChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.FoodTypeChanged(
                    inputString
                )
            )
        },

        onPhoneChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.PhoneChanged(
                    inputString
                )
            )
        },
        onAlternatePhoneChange = { inputString ->
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.AlternatePhoneChanged(
                    inputString
                )
            )
        },
        onGenderChange = { inputString ->
            changeProfileImageState(inputString)
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.GenderChange(
                    inputString
                )
            )
        },
        onCityChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.CityChanged(
                    inputString
                )
            )
        },
        onCuisineChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.CuisineChange(
                    inputString
                )
            )
        },
        onLanguageChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            editCookProfileViewModel.onUiEvent(
                editCookProfileUiEvent = EditCookProfileUiEvent.LanguageChange(
                    inputString
                )
            )
        },
        onSubmit = {
            editCookProfileViewModel.onUiEvent(editCookProfileUiEvent = EditCookProfileUiEvent.Submit)
        },
        //onSignOutClick = onNavigateToSignOut
    )

}


@Composable
fun ShowSnackbar(addCookProfileViewModel: EditCookProfileViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                addCookProfileViewModel.onProcessSuccess.collectLatest { message: String ->
                    Log.d("TAG", "EditProfile: Event success")
                    snackBarHostState.showSnackbar(message)
                }
            }
        }
    }
}

@Composable
fun ShowCustomDialog(
    title: String,
    addCookProfileViewModel: EditCookProfileViewModel,
    showDialogState: Boolean) {

    val isDismiss = remember { mutableStateOf(true) }

    CustomDialog(
        showDialog = showDialogState,
        isAnimate = isDismiss.value,
        onDismissRequest =  addCookProfileViewModel::onDialogDismiss) {
        ResetWarning(color= DeepGreen, title = title,  onDismissRequest = { })
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    EditCookProfileScreen(onNavigateToAuthenticatedRoute = {}, onNavigateBack = {})
}
