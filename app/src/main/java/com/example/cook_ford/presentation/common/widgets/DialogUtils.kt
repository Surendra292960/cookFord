package com.example.cook_ford.presentation.common.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cook_ford.R
import com.example.cook_ford.presentation.common.customeComposableViews.TitleText
import com.example.cook_ford.presentation.screens.sign_in.SignInForm
import com.example.cook_ford.presentation.screens.sign_in.state.SignInUiEvent
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = {showDialog=true },
            properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )) {
        Surface(shape = RoundedCornerShape(16.dp), color = Color.White) {

            AnimatedVisibility(
                visible = true, //{true /*animateIn && showDialog },
                enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)) + scaleIn(
                    initialScale = .8f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ),
                exit = slideOutVertically { it / 8 } + fadeOut() + scaleOut(targetScale = .95f)) {
                Box(
                    Modifier
                        .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                        .width(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            MaterialTheme.colorScheme.surface,
                        ),
                    contentAlignment = Alignment.Center) {
                   // content()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .imePadding()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {

                        Spacer(modifier = Modifier.height(10.dp))

                        // Image
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()) {
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

                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween) {

                            Spacer(modifier = Modifier.height(10.dp))
                            // Heading Login
                            Text(
                                modifier = Modifier.padding(top = AppTheme.dimens.paddingNormal),
                                text = "Success",
                                textAlign = TextAlign.Center,
                                color = Color.Green
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                                Button(onClick = {  showDialog = false  }) { Text(text = "Reset") }
                            }
                        }
                    }
                }

                DisposableEffect(Unit) {
                    onDispose {
                        showDialog = false
                    }
                }
            }
        }
    }



@Preview
@Composable
fun Preview(){
    Cook_fordTheme {
        val showDialog =  remember { mutableStateOf(true) }
        CustomDialog(
            value = "Data", setShowDialog = { showDialog.value = it}) {
            Log.i("HomePage","HomePage : $it")
        }
    }
}
