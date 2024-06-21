package com.example.cook_ford.presentation.screens.authenticated.accounts.call_credit_screen_component

import android.graphics.Color.parseColor
import android.graphics.PorterDuff
import android.util.Log
import android.widget.RatingBar
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.cook_ford.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(){

    val pagerState  = rememberPagerState(initialPage =  0)

    LaunchedEffect(key1 = pagerState.currentPage) {
        delay(3000)
        with(pagerState) {
            val target = if (currentPage < pageCount - 1) currentPage + 1 else 0

            tween<Float>(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
            animateScrollToPage(page = target )
        }
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
      /*  Column(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "View Pager Slide",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }*/

        Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(
            state = pagerState,
            count = 5,
            modifier = Modifier
                .fillMaxSize()
                .height(150.dp)
                .weight(1f)
                .padding(0.dp, 40.dp, 0.dp, 40.dp)
        ) { page ->
            kidsList.getOrNull(page % (kidsList.size))?.let {
                Card(modifier = Modifier
                   /* .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(start = 0.85f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }
                        alpha = lerp(start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                    }*/
                    .fillMaxWidth()
                    .padding(25.dp, 0.dp, 25.dp, 0.dp), shape = RoundedCornerShape(20.dp)) {
                    val newKids = kidsList[page]
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                    //    .align(Alignment.CenterHorizontally)
                    ) {
                        Log.d("TAG", "ViewPagerSlider 22: ${kidsList[page]}")
                        Image(painter = painterResource(id = R.drawable.ic_chef_round),
                            contentDescription = "Image",
                            //modifier = Modifier.fillMaxSize()
                        )

                        /*Column(modifier = Modifier.align(Alignment.BottomStart).padding(15.dp)) {

                            Text(
                                text = newKids.title,
                                style = MaterialTheme.typography.h5,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )*//*
                            val ratingBar = RatingBar(
                                LocalContext.current, null, 8
                            ).apply {
                                rating = newKids.rating
                               *//**//* progressDrawable.setColorFilter(
                                    parseColor("#FF0000"),
                                    PorterDuff.Mode.SRC_ATOP
                                )*//**//*
                            }

                            AndroidView(factory ={ratingBar},
                                modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                            )*//*
                            Text(
                                text = newKids.desc,
                                style = MaterialTheme.typography.body1,
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(0.dp,8.dp,0.dp,0.dp)
                            )
                        }*/
                    }
                }
            }
        }
    }
}