package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.LocationPermissionScreen
import com.example.cook_ford.presentation.component.rememberImeState
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_address.state.AddressUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.utils.Utility.getCurrentLocation
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddressScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = hiltViewModel(),
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val addressState by remember { addressViewModel.addressState }
    val showDialogState: Boolean by addressViewModel.showDialog.collectAsState()
    val signUpResponse by addressViewModel.signUpResponse.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by addressViewModel.viewState.collectAsState()
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
                    location?.let { addressViewModel.setLocation(it) }
                }
            }
        }
    )

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    if (addressState.isSignUpSuccessful) {

        ShowCustomDialog(signUpResponse.message, addressViewModel, showDialogState)

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

                    Box(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                        // Back button
                        IconButton(
                            onClick = { navController.navigateUp() },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBackIosNew,
                                contentDescription = null,
                                modifier = Modifier
                                    .shadow(0.dp)
                                    .clip(CircleShape)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.cook_address_text),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier.align(Alignment.Center)
                        )


                        // Skip Button
                        TextButton(
                            shape = CircleShape,
                            onClick = { /*onSkipClick*/ },
                            modifier = Modifier.align(Alignment.CenterEnd),
                            contentPadding = PaddingValues(5.dp)
                        ) {

                        }
                    }

                    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Add Your Address Here",
                            fontSize = 26.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        //SignUp Form
                        Column(modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingLarge).padding(bottom = AppTheme.dimens.paddingExtraLarge)) {

                            AddressForm(
                                addressState = addressState,
                                viewState = viewState,
                                onAddressChange = { inputString ->
                                    addressViewModel.onUiEvent(
                                        addressUiEvent = AddressUiEvent.AddressChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onCityChange = { inputString ->
                                    addressViewModel.onUiEvent(
                                        addressUiEvent = AddressUiEvent.CityChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onStateChange = { inputString ->
                                    addressViewModel.onUiEvent(
                                        addressUiEvent = AddressUiEvent.StateChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onZipCodeChange = { inputString ->
                                    addressViewModel.onUiEvent(
                                        addressUiEvent = AddressUiEvent.ZipCodeChange(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onSubmit = {
                                    addressViewModel.onUiEvent(addressUiEvent = AddressUiEvent.Submit)
                                }
                            )
                        }
                    }
                }

                ShowSnackbar(
                    addressViewModel,
                    lifecycle,
                    snackBarHostState
                )
            }
        }
    }
}

@Composable
fun ShowSnackbar(addressViewModel: AddressViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
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
    signUpViewModel: AddressViewModel,
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
        AddressScreen( onNavigateToAuthenticatedRoute = {}, navController = NavController(
            LocalContext.current)
        )
    }
}
