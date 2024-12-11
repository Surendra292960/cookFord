package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_cuisine_themes

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.AutoSizeButton
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.SubTitleText
import com.example.cook_ford.presentation.component.widgets.SubmitButton
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_aadhaar.data
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.utils.AppConstants

val item = listOf(1, 2, 3, 4, 5)

@Composable
fun UploadCuisineImagesScreen(
    navController: NavHostController,
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit,
    profileLazyListState: LazyListState = rememberLazyListState()
) {
    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp)
        , horizontalAlignment = Alignment.Start
    ) {

        Column( modifier = Modifier.fillMaxSize()
            .fillMaxSize().weight(1f, fill = false), horizontalAlignment = Alignment.CenterHorizontally) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = profileLazyListState,
                contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
                content = {
                    items(item.size) { index ->
                        AddPhotoCard(
                            item[index]
                        ) { onChange ->
                            Log.d("TAG", "UploadAadhaarScreen: $onChange")
                        }
                    }
                }
            )
        }

        SubmitButton(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).background(Color.Transparent, shape = CircleShape),
            text = stringResource(id = R.string.submit_button_text),
            isLoading = false,
            onClick = {  }
        )
    }
}

@Composable
fun AddPhotoCard(item: Int, onChange: (String) -> Unit) {
    var selectedImages by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImages = uri
            onChange(uri.toString())
        }
    )

    fun launchPhotoPicker() {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    Column(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { launchPhotoPicker() },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Color.Gray),
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier) {
                    AsyncImage(model = if (selectedImages != null) {
                        selectedImages
                    } else {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = AppConstants.ADD_PHOTO,
                                tint = Color.Gray,
                                modifier = Modifier.size(45.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            SubTitleText(
                                modifier = Modifier,
                                text = AppConstants.ADD_PHOTO,
                                textAlign = TextAlign.Start,
                                textColor = Color.Gray,
                                fontWeight = FontWeight.W700
                            )
                        }
                    },
                        contentDescription = "Profile Photo",
                        modifier = Modifier.clip(RectangleShape),
                        contentScale = ContentScale.Crop
                    )
                    if (selectedImages != null) {
                        Row(
                            modifier = Modifier.align(Alignment.BottomEnd),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AutoSizeButton(
                                modifier = Modifier.padding(3.dp),
                                text = stringResource(id = R.string.view_button_text),
                                textColor = Color.Green,
                                buttonColor = Color.White,
                                isLoading = false,
                                onClick = { }
                            )

                            AutoSizeButton(
                                modifier = Modifier.padding(3.dp),
                                text = stringResource(id = R.string.delete_button_text),
                                textColor = Color.White,
                                buttonColor = Color.Red,
                                isLoading = false,
                                onClick = { selectedImages = null }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreViewScreen() {
    UploadCuisineImagesScreen(
        navController = rememberNavController(),
        onNavigateBack = {},
        profileResponse = ProfileResponse(),
        onNavigateToAuthenticatedRoute = {}
    )
}