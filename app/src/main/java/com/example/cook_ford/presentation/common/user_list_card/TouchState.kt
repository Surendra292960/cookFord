package com.example.cook_ford.presentation.common.user_list_card

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cook_ford.presentation.common.customeComposableViews.MediumTitleText
import com.example.cook_ford.presentation.common.customeComposableViews.SubTitleText
import com.example.cook_ford.presentation.theme.AppTheme

enum class TouchState {
    Touched, NotTouched
}

@Preview
@Composable
fun MainScreen() {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
        content = {
            items(getData()) {
                ProductsCard(it)
            }
        })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductsCard(products: ProductModel) {

    var currentState: TouchState by remember { mutableStateOf(TouchState.NotTouched) }

    val transition = updateTransition(targetState = currentState, label = "animation")


    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) { state ->
        if (state == TouchState.Touched) {
            1.3f
        } else {
            1f
        }
    }

    val colorAlpha: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) { state ->
        if (state == TouchState.Touched) {
            1f
        } else {
            0.2f
        }
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(AppTheme.dimens.paddingExtraSmall),
        contentAlignment = Alignment.BottomCenter) {

        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .pointerInteropFilter {
                currentState = when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        TouchState.Touched
                    }

                    else -> {
                        TouchState.NotTouched
                    }
                };true
            },
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp, topEnd = 5.dp, bottomStart = 5.dp)) {

           Column (modifier = Modifier.fillMaxSize()){

               Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                   Row(modifier = Modifier
                       .fillMaxSize()
                       .background(
                           Brush.linearGradient(
                               colors = listOf(
                                   products.color.copy(alpha = 0.2f),
                                   products.color.copy(alpha = 0.2f),
                                   products.color.copy(alpha = colorAlpha),
                               )
                           )
                       ),
                       horizontalArrangement = Arrangement.Start,
                       verticalAlignment = Alignment.CenterVertically) {
                       // ProfilePicture() composable
                       Box(modifier = Modifier
                           .size(80.dp)
                           .clip(CircleShape)
                           .background(Color.White)){
                            Column (modifier = Modifier.wrapContentSize(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally){

                                Image(
                                    painter = painterResource(id = products.image),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(80.dp))
                            }
                       }

                       Spacer(modifier = Modifier.size(size = AppTheme.dimens.paddingNormal))

                       // ProfileContent() composable
                       Box(modifier = Modifier
                           .fillMaxSize()
                           .padding(end = AppTheme.dimens.paddingNormal)) {
                           Column(modifier = Modifier
                               .fillMaxHeight()
                               .padding(start = AppTheme.dimens.paddingNormal),
                               horizontalAlignment = Alignment.Start,
                               verticalArrangement = Arrangement.Center) {

                               Row(modifier = Modifier.fillMaxWidth()) {
                                   MediumTitleText(
                                       modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                                       text = products.title
                                   )
                                   Icon(Icons.Default.Verified, "", modifier = Modifier
                                       .align(Alignment.CenterVertically)
                                       .padding(AppTheme.dimens.paddingSmall))
                               }
                               Row(modifier = Modifier.fillMaxWidth()) {
                                   Icon(Icons.Default.Star, "", modifier = Modifier.align(Alignment.CenterVertically)
                                       .padding(end = AppTheme.dimens.paddingSmall))
                                   Text(
                                       modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                                       text = "5 Ratings"
                                   )
                               }
                               //SubTitle2
                               AssistChip(
                                   onClick = { Log.d("Assist chip", "hello world") },
                                   label = { Text("Domestic") },
                                   leadingIcon = {
                                       Icon(
                                           Icons.Default.Fastfood,
                                           contentDescription = "Localized description",
                                           Modifier.size(AssistChipDefaults.IconSize)
                                       )
                                   }
                               )
                               //SubTitle2
                               SubTitleText(
                                   modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                                   text = "Types of food"
                               )
                               //SubTitle2
                               SubTitleText(
                                   modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                                   text = products.title
                               )

                               /*AnimatedVisibility(modifier = Modifier.padding(end = AppTheme.dimens.paddingSmall), visible = currentState == TouchState.NotTouched) {
                                   Text(
                                       text = products.price,
                                       letterSpacing = 2.sp,
                                       color = MaterialTheme.colorScheme.onPrimary,
                                       style = MaterialTheme.typography.bodySmall,
                                   )
                               }
                               AnimatedVisibility(modifier = Modifier.padding(end = AppTheme.dimens.paddingSmall), visible = currentState == TouchState.Touched) {
                                   Row(verticalAlignment = Alignment.Bottom) {
                                       Text(
                                           text = "Buy Now",
                                           style = MaterialTheme.typography.bodyMedium,
                                           color = MaterialTheme.colorScheme.onPrimary,
                                           letterSpacing = 2.sp
                                       )
                                       Icon(
                                           imageVector = Icons.Rounded.ArrowForward,
                                           contentDescription = null,
                                           Modifier.size(20.dp)
                                       )
                                   }
                               }*/
                           }
                       }
                   }
               }
           }
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimens.paddingSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    //SubTitle1
                   Column {
                       SubTitleText(
                           modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                           text = "Experience"
                       )
                       SubTitleText(
                           modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                           text = "12yr"
                       )
                   }
                    //SubTitle2
                   Column {
                       SubTitleText(
                           modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                           text = "Languages"
                       )
                       SubTitleText(
                           modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                           text = "Hindi English"
                       )
                   }
                    //SubTitle3
                   Column {
                       SubTitleText(
                           modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                           text = "From"
                       )

                       SubTitleText(
                           modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                           text = "Orissa"
                       )
                   }
                }
            }
        }
    }
}