package com.example.cook_ford.presentation.route.topbar_nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.FontName


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingTopBar(scrollBehavior: TopAppBarScrollBehavior, isCollapsed: Boolean, scrollProvider: ScrollState) {
  val lazyListState = rememberLazyListState()
  var scrolledY = 0f
  var previousOffset = 0

  LargeTopAppBar(

    title = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .width(150.dp)
          .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {

        Text(
          text = "Cook Ford",
          fontSize = 20.sp,
          color = Color.Black,
          fontWeight = FontWeight.Normal,
          textAlign = TextAlign.Start,
        )

        Box(
          modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)

            .background(Color.White)
        ) {
          Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {

            Image(
              painter = painterResource(id = R.drawable.profile_circle),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .size(80.dp)
                .graphicsLayer {
                  // if scaleXY equals 0.8.dp then scaleXY.value equals 0.8
                  /*  scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                  translationY = scrolledY * 0.5f
                  previousOffset = lazyListState.firstVisibleItemScrollOffset*/
                })
          }
        }
      }
    },
    colors = TopAppBarDefaults.mediumTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.onSecondary,
      scrolledContainerColor = MaterialTheme.colorScheme.background,
      titleContentColor = if (isCollapsed) {
        MaterialTheme.colorScheme.onBackground
      } else {
        MaterialTheme.colorScheme.onPrimary
      },
    ),
    scrollBehavior = scrollBehavior,
  )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopBar(
  modifier: Modifier = Modifier,
  title: String,
  isVisible: Boolean,
  onNavigateBack: () -> Unit = {},
  actions: @Composable () -> Unit = {}) {
  if (isVisible) {
    TopAppBar(
      title = {
       Row (horizontalArrangement = Arrangement.Start,
         verticalAlignment = Alignment.CenterVertically,
         modifier = modifier.fillMaxWidth()){
         Text(
           text = title,
           color = Color.DarkGray,
           fontSize = 20.sp,
           fontFamily = FontName,
           fontWeight = FontWeight.W600)
       }
      },
      actions = { actions() },
      navigationIcon = {
        IconButton(onClick = { onNavigateBack.invoke() }) {
          Icon(
            imageVector = Icons.Filled.ArrowBackIosNew, tint = Color.DarkGray,
            contentDescription = stringResource(R.string.back)
          )
        }
      },
      modifier = modifier.background(Color.White)
    )
  } /*else {
    TopAppBar(
      title = {
        Text(text = title)
      },
      actions = { actions() },
      modifier = modifier
    )
  }*/
}


/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navigationBack: () -> Unit, scrollBehavior: TopAppBarScrollBehavior, navController: NavHostController, isVisible: Boolean, title: MutableState<String>) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  if (isVisible){
    TopAppBar(
      // in below line we are
      // adding title to our top bar.
      title = {
        // inside title we are
        // adding text to our toolbar.
        Text(
          text = title.value,
          // below line is use
          // to give text color.
          color = Color.Gray
        )
      },
      navigationIcon = {
        // navigation icon is use
        // for drawer icon.
        IconButton(onClick = {navigationBack.invoke()}) {
          // below line is use to
          // specify navigation icon.
          Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "")
        }
      },
      colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.onSecondary,
        scrolledContainerColor = MaterialTheme.colorScheme.background,
      ),

      scrollBehavior = scrollBehavior,
    )
  }
}
*/
