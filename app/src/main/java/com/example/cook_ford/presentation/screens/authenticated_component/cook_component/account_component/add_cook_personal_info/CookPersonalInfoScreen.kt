package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.LocationPermissionScreen
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.state.CookPersonalInfoUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.utils.AppConstants
import com.example.cook_ford.utils.Utility.getCurrentLocation
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun CookPersonalInfoScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    val cookPersonalInfoViewModel: CookPersonalInfoViewModel = hiltViewModel()
    val cookPersonalInfoState by remember { cookPersonalInfoViewModel.cookPersonalInfoState }
    val showDialogState: Boolean by cookPersonalInfoViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by cookPersonalInfoViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val mContext = LocalContext.current
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    var isPermissionGranted by remember { mutableStateOf(false) }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(mContext) }

    LocationPermissionScreen(
        onPermissionGranted = { isGranted->
            if (isGranted) {
                isPermissionGranted = true
                getCurrentLocation(fusedLocationClient) { location ->
                    Log.d("TAG", "SignUpScreen location : $location")
                    location?.let { cookPersonalInfoViewModel.setLocation(it) }
                }
            }
        }
    )

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Progressbar(cookPersonalInfoState.isLoading)
    Log.d("TAG", "ProfileListScreen isLoading: ${cookPersonalInfoState.isLoading}")
    LaunchedEffect(key1 = Unit) {
        cookPersonalInfoViewModel.setProfileData(profileResponse)

    }

    if (cookPersonalInfoState.isSuccessful) {

        Log.d("TAG", "SignInScreen: $showDialogState")
        /**
         * Navigate to Authenticated navigation route
         * once signIn is successful
         */
        if (!showDialogState){
            LaunchedEffect(key1 = true) {
                onNavigateToAuthenticatedRoute.invoke()
            }
        }
    } else {
        // Full Screen Content
        if (isPermissionGranted){
            Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { paddingValues ->

                Column(modifier = Modifier.background(Color.White).fillMaxSize().navigationBarsPadding().padding(paddingValues)) {

                    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {

                        Spacer(modifier = Modifier.height(20.dp))

                        TitleText(
                            modifier = Modifier,
                            text = AppConstants.PERSONAL_INFO,
                            textAlign = TextAlign.Start,
                            textColor = Color.DarkGray,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        //SignUp Form
                        Column(modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingLarge).padding(bottom = AppTheme.dimens.paddingExtraLarge)) {

                            CookPersonalInfoForm(
                                cookPersonalInfoState = cookPersonalInfoState,
                                viewState = viewState,
                                onFirstNameChange = { inputString ->
                                    cookPersonalInfoViewModel.onUiEvent(
                                        cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.FirstNameChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onLastNameChange = { inputString ->
                                    cookPersonalInfoViewModel.onUiEvent(
                                        cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.LastNameChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onAddressChange = { inputString ->
                                    cookPersonalInfoViewModel.onUiEvent(
                                        cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.AddressChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onCityChange = { inputString ->
                                    cookPersonalInfoViewModel.onUiEvent(
                                        cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.CityChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onStateChange = { inputString ->
                                    cookPersonalInfoViewModel.onUiEvent(
                                        cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.StateChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onZipCodeChange = { inputString ->
                                    cookPersonalInfoViewModel.onUiEvent(
                                        cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.ZipCodeChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onSubmit = {
                                    cookPersonalInfoViewModel.onUiEvent(cookPersonalInfoUiEvent = CookPersonalInfoUiEvent.Submit)
                                }
                            )
                        }
                    }
                }

                ShowSnackbar(
                    cookPersonalInfoViewModel,
                    lifecycle,
                    snackBarHostState
                )
            }
        }
    }
}

@Composable
fun ShowSnackbar(addressViewModel: CookPersonalInfoViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                addressViewModel.onProcessSuccess.collectLatest { message: String ->
                    Log.d("TAG", "SignInForm: Event success")
                    snackBarHostState.showSnackbar(message)
                }
            }
        }
    }
}

@Composable
fun ShowCustomDialog(
    title: String,
    signUpViewModel: CookPersonalInfoViewModel,
    showDialogState: Boolean) {

    val isDismiss = remember { mutableStateOf(true) }

    CustomDialog(
        showDialog = showDialogState,
        isAnimate = isDismiss.value,
        onDismissRequest = signUpViewModel::onDialogDismiss) {
        ResetWarning(color= DeepGreen, title = title,  onDismissRequest = { })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    Cook_fordTheme {
        CookPersonalInfoScreen(
            onNavigateBack = { },
            onNavigateToAuthenticatedRoute = {},
        )
    }
}
