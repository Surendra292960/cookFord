package com.example.cook_ford.presentation.screens.authenticated.account.cook
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.dialog.CustomDialog
import com.example.cook_ford.presentation.component.widgets.dialog.ResetWarning
import com.example.cook_ford.presentation.component.widgets.snack_bar.MainViewState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileState
import com.example.cook_ford.presentation.screens.authenticated.account.cook.state.AddCookProfileUiEvent
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


data class Option(val option: String="", val selected: Boolean=false)
@Composable
fun AddCookProfileScreen(
    uploadProfileImage: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val addCookProfileViewModel: AddCookProfileViewModel = hiltViewModel()
    val addCookProfileState by remember { addCookProfileViewModel.addCookProfileState }
    val showDialogState: Boolean by addCookProfileViewModel.showDialog.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val changeProfileState = remember { mutableStateOf("") }
    val pickImageRequest = remember { mutableStateOf(false) }
    val viewState: MainViewState by addCookProfileViewModel.viewState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    if (addCookProfileState.isCookAddedSuccessful) {

        ShowCustomDialog("message", addCookProfileViewModel, showDialogState)

        Log.d("TAG", "EditProfileScreen: $showDialogState")
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {
            PhotoPickerDemoScreen()
            // Profile Image
            ProfileImage(
                pickProfileImage = {
                    pickImageRequest.value  = true
            }, changeProfileState)

            JobTypeSection()

            // EditProfile Inputs Composable
            CookProfileForm(
                addCookProfileState,
                addCookProfileViewModel,
                viewState,
                changeProfileImageState = {
                    changeProfileState.value = it
                    Log.d("TAG", "EditProfileScreen: ${changeProfileState.value}")
                })
            // Show Snackbar
            ShowSnackbar(addCookProfileViewModel, lifecycle, snackBarHostState)

            if (pickImageRequest.value){
                pickImageRequest.value = false
            }
        }
    }
}


@Composable
fun ProfileImage(pickProfileImage:()->Unit, changeProfileState: MutableState<String>) {
    Log.d("TAG", "ProfileImage: ${changeProfileState.value}")

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .clickable {
            pickProfileImage.invoke()
        }
        .padding(top = 16.dp), contentAlignment = Alignment.TopCenter) {

        Card(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape),
            shape = CircleShape,
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            if (changeProfileState.value == "Female") {
                Image(
                    painter = painterResource(id = R.drawable.female_chef),
                    contentDescription = "Profile Photo",
                    modifier = Modifier,
                    contentScale = ContentScale.Crop,
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.male_chef),
                    contentDescription = "Profile Photo",
                    modifier = Modifier,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}
@Composable
fun PhotoPickerDemoScreen() {
    //The URI of the photo that the user has picked
    var photoUri: Uri? by remember { mutableStateOf(null) }

    //The launcher we will use for the PickVisualMedia contract.
    //When .launch()ed, this will display the photo picker.
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            //When the user has selected a photo, its URI is returned here
            photoUri = uri
        }


    Column {
        launcher.launch(
            PickVisualMediaRequest(
                //Here we request only photos. Change this to .ImageAndVideo if you want videos too.
                //Or use .VideoOnly if you only want videos.
                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
        Log.d("TAG", "PhotoPickerDemoScreen: ")

        if (photoUri != null) {
            //Use Coil to display the selected image
            val painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = photoUri)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .border(6.0.dp, Color.Gray),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CookProfileForm(
    addCookProfileState: AddCookProfileState,
    addCookProfileViewModel: AddCookProfileViewModel,
    viewState: MainViewState,
    changeProfileImageState: (String) -> Unit) {

    // EditProfile Inputs Composable
    AddCookProfileForm(
        addCookProfileState = addCookProfileState,
        viewState = viewState,

        onUserNameChange = { inputString ->
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
        ResetWarning(color= Color.Green, title = title,  onDismissRequest = { })
    }
}



@Composable
fun JobTypeSection() {
    val jobTypeItems = listOf(
    "Part time\n(Daily/Occasional meals)",
    "Full day\n(Domestic)",
    "Live in\n(Domestic)",
    "Catering\n(Parties & Events)")
    val jobTypeItemsLast = listOf("Restaurant chef\n(Commercial)")
    val columnsCount = 2
    LazyVerticalGrid(
        columns = GridCells.Fixed(columnsCount),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(jobTypeItems.size) { index ->
            JobTypeItem(
                label = jobTypeItems[index],
                selected = index == 0,
                enabled = index == 0
            )
            Log.d("TAG", "JobTypeSection: xxxxxx")
        }

        items(jobTypeItemsLast.size, span = { GridItemSpan(columnsCount) }) { index->
            JobTypeItem(
                label =jobTypeItemsLast[index],
                selected = false,
                enabled =false
            )
            Log.d("TAG", "JobTypeSection: yyyyyy")
        }
    }
}

@Composable
fun JobTypeItem(
    label: String,
    selected: Boolean,
    enabled: Boolean) {
    val addCookProfileViewModel: AddCookProfileViewModel = hiltViewModel()
    val isSelected = remember { mutableStateOf(selected) }
    Log.d("TAG", "JobTypeItem: ${Gson().toJson(addCookProfileViewModel.selectedItem)}")
    if(isSelected.value) addCookProfileViewModel.selectedItem.add(label)
    Box(modifier = Modifier
        .height(50.dp)
        .border(1.dp, Color.Gray)
        .background(if (isSelected.value) Color.LightGray else Color.White)
        .fillMaxWidth()
        .clickable {
            if (!isSelected.value) {
                isSelected.value = true
                Log.d("TAG", "JobTypeItem: if ")
                addCookProfileViewModel.selectedItem.add(label)
            } else {
                isSelected.value = false
                addCookProfileViewModel.selectedItem.removeIf { it == label }
                Log.d("TAG", "JobTypeItem: else ")
            }

        }) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(8.dp),
            textAlign = TextAlign.Center,
            color = if (isSelected.value) Color.White else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    AddCookProfileScreen(onNavigateToAuthenticatedRoute = {}, uploadProfileImage = {}, onNavigateBack = {})
}
