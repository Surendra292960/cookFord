package com.example.cook_ford.presentation.common.widgets.topbar_nav

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTopBar(scrollBehavior: TopAppBarScrollBehavior, isCollapsed: Boolean, scrollProvider: ScrollState) {
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