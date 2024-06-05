package com.example.cook_ford.presentation.component.widgets

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cook_ford.R

@Composable
fun ProfileImage(changeProfileState: MutableState<String>, onChange: (String) -> Unit) {
    var selectedImages by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
                uri -> selectedImages = uri
            onChange(uri.toString())
        }
    )

    fun launchPhotoPicker() {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {

        Card(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .clickable {
                    launchPhotoPicker()
                },
            shape = CircleShape,
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            if (changeProfileState.value == "Female") {
                Log.d("TAG", "ProfileImage : ${changeProfileState.value}")
                AsyncImage(
                    model = if (selectedImages != null) selectedImages else R.drawable.female_chef,
                    contentDescription = "Profile Photo",
                    modifier = Modifier.clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }else{
                Log.d("TAG", "ProfileImage : ${changeProfileState.value}")
                AsyncImage(
                    model = if (selectedImages != null) selectedImages else R.drawable.male_chef,
                    contentDescription = "Profile Photo",
                    modifier = Modifier.clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}