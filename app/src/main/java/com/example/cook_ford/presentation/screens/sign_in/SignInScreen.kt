package com.example.cook_ford.presentation.screens.sign_in
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.presentation.common.customeComposableViews.TitleText
import com.example.cook_ford.presentation.screens.sign_in.state.SignInUiEvent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.presentation.common.widgets.CustomDialog
import com.example.cook_ford.presentation.common.widgets.DialogState
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {

    val _signInState by remember { signInViewModel.signInState }
    val dialogState by remember { mutableStateOf(signInViewModel.dialogState) }

   /* if (loginState.isLoading) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    }*/

    if (_signInState.isSignInSuccessful) {
        /**
         * Navigate to Authenticated navigation route
         * once login is successful
         */
        DisplayDialog(dialogState)
        LaunchedEffect(key1 = true) {
            delay(2000)
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {

        Surface {

            // Full Screen Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                // Main card Content for Login
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimens.paddingLarge)) {

                    Spacer(modifier = Modifier.height(10.dp))

                    // Image
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Change the logo
                        Image(
                            painter = painterResource(id = R.drawable.cook_ford_rounded_logo),
                            contentDescription = "Logo",
                            //modifier = Modifier.scale(3f))
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = AppTheme.dimens.paddingLarge)
                            .padding(bottom = AppTheme.dimens.paddingExtraLarge)) {

                        // Heading Login
                        TitleText(
                            modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                            text = stringResource(id = R.string.sign_in_heading_text)
                        )

                        // Login Inputs Composable
                        SignInForm(
                            loginState = _signInState,
                            onUserNameChange = { inputString ->
                                signInViewModel.onUiEvent(
                                    signInUiEvent = SignInUiEvent.UserNameChanged(
                                        inputString
                                    )
                                )
                            },
                            onPasswordChange = { inputString ->
                                signInViewModel.onUiEvent(
                                    signInUiEvent = SignInUiEvent.PasswordChanged(
                                        inputString
                                    )
                                )
                            },
                            onSubmit = {
                                signInViewModel.onUiEvent(signInUiEvent = SignInUiEvent.Submit)
                            },
                            onForgotPasswordClick = onNavigateToForgotPassword
                        )
                    }

                    // Register Section
                    Row(modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        // Don't have an account?
                        Text(text = stringResource(id = R.string.do_not_have_account))

                        //Register
                        Text(
                            modifier = Modifier
                                .padding(start = AppTheme.dimens.paddingExtraSmall)
                                .clickable {
                                    onNavigateToRegistration.invoke()
                                },
                            text = stringResource(id = R.string.register),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayDialog(showDialog: MutableState<DialogState>){
    if(showDialog.value.showDialogState) {
        CustomDialog(
            value = showDialog.value.message, setShowDialog = { showDialog.value.showDialogState = it }) {
            Log.d("HomePage", "HomePage : ${showDialog.value.message}")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreen() {
    Cook_fordTheme {
        SignInScreen(
            onNavigateToForgotPassword = {},
            onNavigateToRegistration = {},
            onNavigateToAuthenticatedRoute = {}
        )
    }
}
