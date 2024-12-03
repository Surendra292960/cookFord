package com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.cook_sign_up
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
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.cook_sign_up.state.CookUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.utils.Utility.getCurrentLocation
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun CookSignUpScreen(
    navController: NavController,
    signUpViewModel: CookSignUpViewModel = hiltViewModel(),
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val signUpState by remember { signUpViewModel.signUpState }
    val showDialogState: Boolean by signUpViewModel.showDialog.collectAsState()
    val signUpResponse by signUpViewModel.signUpResponse.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val viewState: MainViewState by signUpViewModel.viewState.collectAsState()
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
                    location?.let { signUpViewModel.setLocation(it) }
                }
            }
        }
    )

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value){
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    if (signUpState.isSignUpSuccessful) {

        ShowCustomDialog(signUpResponse.message, signUpViewModel, showDialogState)

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
                            text = stringResource(id = R.string.sign_up_heading_text),
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

                            /*   Text(text = "Skip",
                               style = MaterialTheme.typography.bodyLarge,
                               color = Color.Black,
                               textAlign = TextAlign.Center,
                               fontSize = 17.sp,
                               fontWeight = FontWeight.W600,
                               modifier = Modifier
                                   .shadow(0.dp)
                                   .clip(CircleShape))*/
                        }
                    }

                    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {

                        /*      Spacer(modifier = Modifier.height(10.dp))

                    // Image
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        // Change the logo
                        Image(
                            painter = painterResource(id = R.drawable.cook_ford_rounded_logo),
                            contentDescription = "Logo",
                            //modifier = Modifier.scale(3f))
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                        )
                    }
    */
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Welcome",
                            fontSize = 26.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            fontSize = 16.sp,
                            text = "Sign Up here to continue",
                            color = Color.Gray,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        //SignUp Form
                        Column(modifier = Modifier.padding(horizontal = AppTheme.dimens.paddingLarge).padding(bottom = AppTheme.dimens.paddingExtraLarge)) {

                            CookSignUpForm(
                                signUpState = signUpState,
                                viewState = viewState,
                                onUserNameChange = { inputString ->
                                    signUpViewModel.onUiEvent(
                                        signUpUiEvent = CookUiEvent.UserNameChanged(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onEmailChange = { inputString ->
                                    signUpViewModel.onUiEvent(
                                        signUpUiEvent = CookUiEvent.EmailChanged(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onPasswordChange = { inputString ->
                                    signUpViewModel.onUiEvent(
                                        signUpUiEvent = CookUiEvent.PasswordChanged(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onConfirmPasswordChange = { inputString ->
                                    signUpViewModel.onUiEvent(
                                        signUpUiEvent = CookUiEvent.ConfirmPasswordChanged(
                                            inputValue = inputString
                                        )
                                    )
                                },
                                onGenderChange = { inputString ->
                                    signUpViewModel.onUiEvent(
                                        signUpUiEvent = CookUiEvent.GenderChange(
                                            inputString
                                        )
                                    )
                                },
                                onSubmit = {
                                    signUpViewModel.onUiEvent(signUpUiEvent = CookUiEvent.Submit)
                                }
                            )
                        }
                    }
                }

                ShowSnackbar(
                    signUpViewModel,
                    lifecycle,
                    snackBarHostState
                )
            }
        }
    }
}

@Composable
fun ShowSnackbar(signUpViewModel: CookSignUpViewModel, lifecycle: Lifecycle, snackBarHostState: SnackbarHostState) {
    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                signUpViewModel.onProcessSuccess.collectLatest { message: String ->
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
    signUpViewModel: CookSignUpViewModel,
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
        CookSignUpScreen( onNavigateToAuthenticatedRoute = {}, navController = NavController(
            LocalContext.current)
        )
    }
}
