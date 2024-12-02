package com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.add_cook_screen_component
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.add_cook_screen_component.state.AddCookProfileState
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.add_cook_screen_component.state.AddCookProfileUiEvent
import com.example.cook_ford.presentation.theme.DeepGreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun AddCookProfileScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val addCookProfileViewModel: AddCookProfileViewModel = hiltViewModel()
    val addCookProfileState by remember { addCookProfileViewModel.addCookProfileState }
    val showDialogState: Boolean by addCookProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val changeProfileState = remember { mutableStateOf("") }
    val viewState: MainViewState by addCookProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle


    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    if (addCookProfileState.isCookAddedSuccessful) {

        ShowCustomDialog("message", addCookProfileViewModel, showDialogState)

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
                addCookProfileState = addCookProfileState,
                addCookProfileViewModel = addCookProfileViewModel,
                viewState = viewState,
                changeProfileImageState = {
                    changeProfileState.value = it
                    Log.d("TAG", "AddCookProfileScreen: ${changeProfileState.value}")
                })
            // Show Snackbar
            ShowSnackbar(addCookProfileViewModel, lifecycle, snackBarHostState)
        }
    }
}

@Composable
fun CookProfileForm(
    changeProfileState:MutableState<String>,
    addCookProfileState: AddCookProfileState,
    addCookProfileViewModel: AddCookProfileViewModel,
    viewState: MainViewState,
    changeProfileImageState: (String) -> Unit) {

    // EditProfile Inputs Composable
    AddCookProfileForm(
        changeProfileState = changeProfileState,
        addCookProfileState = addCookProfileState,
        viewState = viewState,


        onProfileImageChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.ProfileImageChanged(
                    inputString
                )
            )
        },

        onJobTypeChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.JobTypeChange(
                    inputString
                )
            )
        },

        onUserNameChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.UserNameChanged(
                    inputString
                )
            )
        },
        onPhoneChange = { inputString ->
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.PhoneChanged(
                    inputString
                )
            )
        },
        onAlternatePhoneChange = { inputString ->
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.AlternatePhoneChanged(
                    inputString
                )
            )
        },
        onGenderChange = { inputString ->
            changeProfileImageState(inputString)
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.GenderChange(
                    inputString
                )
            )
        },
        onCityChange = { inputString ->
            Log.d("TAG", "CookProfileForm: $inputString")
            addCookProfileViewModel.onUiEvent(
                addCookProfileUiEvent = AddCookProfileUiEvent.CityChanged(
                    inputString
                )
            )
        },
        onSubmit = {
            addCookProfileViewModel.onUiEvent(addCookProfileUiEvent = AddCookProfileUiEvent.Submit)
        },
        //onSignOutClick = onNavigateToSignOut
    )

}


@Composable
fun ShowSnackbar(addCookProfileViewModel: AddCookProfileViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
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
    addCookProfileViewModel: AddCookProfileViewModel,
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
    AddCookProfileScreen(onNavigateToAuthenticatedRoute = {}, onNavigateBack = {})
}
