package com.example.cook_ford.presentation.component

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun LocationPermissionScreen(
    onPermissionGranted: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val hasPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val showRationale = remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val allGranted = permissions.values.all { it }
            hasPermission.value = allGranted
            if (allGranted) {
                onPermissionGranted.invoke(hasPermission.value)
            } else {
                onPermissionGranted.invoke(hasPermission.value)
            }
        }
    )

    // Check if permission is granted and request if not
    if (!hasPermission.value) {
        // Check if we should show rationale
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            // Show rationale dialog
            showRationale.value = true
        } else {
            // Directly launch permission request
            LaunchedEffect(Unit) {
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    // Rationale dialog
    if (showRationale.value) {
        Dialog(
            onDismissRequest = { showRationale.value = false }, properties = DialogProperties(
                dismissOnBackPress = false, dismissOnClickOutside = false
            )
        ) {
            ElevatedCard(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().padding(0.dp).height(IntrinsicSize.Min),
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Text(
                        text = "Location Permission Required",
                        modifier = Modifier.padding(8.dp, 16.dp, 8.dp, 2.dp)
                            .align(Alignment.CenterHorizontally).fillMaxWidth(), fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "This app requires location access to provide location-based features. Please grant the permission.",
                        modifier = Modifier.padding(8.dp, 2.dp, 8.dp, 16.dp)
                            .align(Alignment.CenterHorizontally).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    HorizontalDivider(
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth().width(1.dp)
                    )
                    Row(Modifier.padding(top = 0.dp)) {

                        TextButton(
                            onClick = { showRationale.value = false },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .weight(1F)
                                .border(0.dp, Color.Transparent)
                                .height(48.dp),
                            shape = RoundedCornerShape(0.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = "Cancel", color = Color.Blue)
                        }
                        VerticalDivider(
                            color = Color.Gray, modifier =
                            Modifier.fillMaxHeight().width(1.dp)
                        )

                        TextButton(
                            onClick = {
                                showRationale.value = false
                                locationPermissionLauncher.launch(
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                    )
                                )
                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .weight(1F)
                                .border(0.dp, color = Color.Transparent)
                                .height(48.dp),
                            shape = RoundedCornerShape(0.dp),
                            contentPadding = PaddingValues()
                        ) {
                            Text(text = "Ok", color = Color.Red)
                        }
                    }
                }
            }
        }
    }

    onPermissionGranted.invoke(hasPermission.value)
    // UI to show permission status
    Text(
        text = if (hasPermission.value) "" else "Location permission denied",
        modifier = Modifier.padding(16.dp)
    )
}

