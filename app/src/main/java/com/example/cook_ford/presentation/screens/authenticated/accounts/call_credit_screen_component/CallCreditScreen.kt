package com.example.cook_ford.presentation.screens.authenticated.accounts.call_credit_screen_component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.DefaultBackArrow
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.FontName
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CallCreditScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.Start) {

        Row(modifier = Modifier.fillMaxSize().background(Color.White).padding(top = 20.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(0.3f)) {
                DefaultBackArrow(onClick = {
                    onNavigateBack.invoke()
                })
            }
            Box(modifier = Modifier.weight(1.0f).padding(top = 20.dp)) {
                Text(
                    text = "Your desire cook is just \n a call away",
                    color = Color.DarkGray,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column( modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .weight(1f, false)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(40.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
                Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier .padding(start = 20.dp, end = 20.dp)){
                Icon(Icons.Filled.VerifiedUser, contentDescription = null, tint = DeepGreen, modifier = Modifier.size(40.dp))
                Text(
                    text = "Exclusive Support for 15 days!",
                    style = MaterialTheme.typography.subtitle2,
                    fontFamily = FontName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Experience peace of mind with our hassle-free all credit refund policy!",
                style = MaterialTheme.typography.subtitle2,
                fontFamily = FontName,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier .padding(start = 20.dp, end = 20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray)) {
                        append("Easy refund when cook is ")
                    }
                    withStyle(style = SpanStyle(color = DeepGreen)) {
                        append("not reachable")
                    }
                    withStyle(style = SpanStyle(color = Color.DarkGray)) {
                        append(" or ")
                    }
                    withStyle(style = SpanStyle(color = DeepGreen)) {
                        append("unavailable for the selected locality")
                    }
                    withStyle(style = SpanStyle(color = Color.DarkGray)) {
                        append(" or ")
                    }
                    withStyle(style = SpanStyle(color = DeepGreen)) {
                        append("for the mentioned time.")
                    }
                },
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 17.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {

                HorizontalDivider(modifier = Modifier.weight(1f))

                androidx.compose.material.Text(
                    text = "Contact cooks directly!",
                    fontSize = 15.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )

                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(15.dp))

            ElevatedCard(modifier = Modifier.padding(bottom = 5.dp, start = 10.dp, end = 10.dp), colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall), shape = RoundedCornerShape(8.dp), onClick = { /*TODO*/ }) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(17.dp)){

                    Column (horizontalAlignment = Alignment.Start){
                        Text(
                            text = "12 call credits",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray,
                        )

                        Text(
                            text = "Never Expires",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Rs. 1499  ",
                                fontFamily = FontName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W900,
                                color = Color.Gray,
                                style = TextStyle(textDecoration = TextDecoration.LineThrough)
                            )

                            Text(
                                text = "Rs. 799/-",
                                style = MaterialTheme.typography.subtitle2,
                                fontFamily = FontName,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                            )
                        }

                        Text(
                            text = "47% off!!",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Red,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            ElevatedCard(modifier = Modifier.padding(bottom = 5.dp, start = 10.dp, end = 10.dp), colors = CardDefaults.cardColors(
                DeepGreen), elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(17.dp)){

                    Column (horizontalAlignment = Alignment.Start){
                        Text(
                            text = "9 call credits",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )

                        Text(
                            text = "Never Expires",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Rs. 1049  ",
                                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                fontFamily = FontName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W900,
                                color = Color.White,
                            )

                            Text(
                                text = "Rs. 599/-",
                                style = MaterialTheme.typography.subtitle2,
                                fontFamily = FontName,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }

                        Text(
                            text = "43% off!!",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            ElevatedCard(modifier = Modifier.padding(bottom = 5.dp, start = 10.dp, end = 10.dp), colors = CardDefaults.cardColors(Color.White),elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxSize()
                    .padding(17.dp)){

                    Column (horizontalAlignment = Alignment.Start){
                        Text(
                            text = "7 call credits",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray,
                        )

                        Text(
                            text = "Never Expires",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Rs. 749  ",
                                fontFamily = FontName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W900,
                                color = Color.Gray,
                                style = TextStyle(textDecoration = TextDecoration.LineThrough)
                            )

                            Text(
                                text = "Rs. 499/-",
                                style = MaterialTheme.typography.subtitle2,
                                fontFamily = FontName,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                            )
                        }

                        Text(
                            text = "34% off!!",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Red,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "How it works?",
                style = MaterialTheme.typography.subtitle2,
                fontFamily = FontName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
            )

            Spacer(modifier = Modifier.height(20.dp))

            HowItWorks(modifier = Modifier.padding(bottom = 20.dp))

            Spacer(modifier = Modifier.height(20.dp))

            CollapsableLazyColumn(
                sections = listOf(
                    CollapsableSection(
                        title = "What are call credits?",
                        details = "That will defeat the whole purpose of LazyColumn. The lazy part just won't work when you use it"
                    ),
                    CollapsableSection(
                        title = "How can I claim the call credit refund?",
                        details = "like that. You could just go with normal Column and it would make no difference at all in terms of"
                    ),
                    CollapsableSection(
                        title = "How to get the background verification done for the cook?",
                        details = "performance. Especially with 10 items, with such number the LazyColumn is huge overkill. Bonus for your case - Column supports wrapContentHeight()."
                    ),
                ),
            )

            Spacer(modifier = Modifier.height(40.dp))

            ContentView()

            Spacer(modifier = Modifier.height(20.dp))
        }

        Column(modifier = Modifier
            .background(Color.White)){
            HorizontalDivider(modifier = Modifier.height(1.dp))
            Spacer(modifier = Modifier.height(10.dp))

            Row (modifier = Modifier
                .background(Color.White)
                .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){

                Text(
                    modifier = Modifier
                        .weight(0.3f),
                    text = "Cancel",
                    style = MaterialTheme.typography.subtitle2,
                    fontFamily = FontName,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W600,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(50.dp))
                OutlinedSmallSubmitButton(
                    modifier = Modifier
                        .weight(0.7f),
                    textColor = Color.Gray,
                    text = stringResource(id = R.string.submit_button_text),
                    isLoading = false,
                    onClick = { /*onSubmit*/ }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}


//@ExperimentalFoundationApi
@Composable
fun HowItWorks( modifier: Modifier = Modifier) {
    //Log.d("TAG", "CuisineImages topCuisineUrls : $topCuisineUrls")
    val listState = rememberLazyListState()

    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        LazyRow(state = listState, modifier = modifier.scale(1.01f)) {
            items(5) { index->
                Card(modifier = Modifier
                    .width(300.dp)
                    .height(150.dp)
                    .padding(horizontal = 5.dp),
                    elevation = AppTheme.dimens.paddingSmall) {
                    Text(
                        text = "How it works?",
                        style = MaterialTheme.typography.subtitle2,
                        fontFamily = FontName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerItem(image: Int, pagerState:PagerState, page: Int = pagerState.currentPage) {

    Box(modifier = Modifier
        .size(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.Black)
        .graphicsLayer {
            // Calculate the absolute offset for the current page from the
            // scroll position. We use the absolute value which allows us to mirror
            // any effects for both directions
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.pageOffset
                    ).absoluteValue

            // We animate the alpha, between 50% and 100%
            alpha = lerp(
                start = 0.5f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        },
        contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource(id = image),
            contentScale = ContentScale.Crop,
            contentDescription = "Banner Image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
    }
}


@Composable
fun CollapsableLazyColumn(sections: List<CollapsableSection>, modifier: Modifier = Modifier) {

    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }

    Text(
        text = "FAQs",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        )

    Spacer(modifier = Modifier.height(10.dp))

    Column( modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clickable {
                        collapsedState[i] = !collapsed
                    }) {

                Text(
                    dataItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    Icons.Default.run {
                        if (collapsed)
                            KeyboardArrowDown
                        else
                            KeyboardArrowUp
                    },
                    contentDescription = "",
                    tint = Color.DarkGray,
                )
            }

            if (!collapsed) {
                Row {
                    Text(text = dataItem.details,
                        fontSize = 15.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentView() {

    val list = listOf(R.drawable.female_chef, R.drawable.male_chef, R.drawable.female_chef, R.drawable.spin_wheel,R.drawable.female_chef,)

    val pagerState: PagerState = rememberPagerState(initialPage = list.size)

    Text(
        text = "Customer Reviews",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color.DarkGray,
    )

    Spacer(modifier = Modifier.height(20.dp))

    HorizontalPager(
        modifier = Modifier.fillMaxWidth(),
        count = Int.MAX_VALUE,
        itemSpacing = 15.dp,
        contentPadding = PaddingValues(horizontal = 10.dp),
        state = pagerState) { index ->

        list.getOrNull(index % (list.size))?.let { item ->
            Card(
                border = BorderStroke(1.dp,Color.Gray),
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(item).absoluteValue
                        lerp(
                            start = 0.95f,
                            stop = 2f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }
                        alpha = lerp(
                            start = 1.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(1f, 1f)
                        )
                    }
                    .fillMaxSize(),
                shape = RoundedCornerShape(10.dp)){

                /*Image(
                    painter = painterResource(id = item),
                    contentDescription = "Image $item",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )*/
                Column (horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 15.dp)) {

                    UsersProfileList()

                    Text(
                        text = "Calculate the absolute offset for the current page from the" +
                                "scroll position. We use the absolute value which allows us to mirror" +
                                "Calculate the absolute offset for the current page from the" +
                                "scroll position. We use the absolute value which allows us to mirror",
                        style = MaterialTheme.typography.subtitle2,
                        fontFamily = FontName,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
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
    }
}



@Composable
fun UsersProfileList() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Box(modifier = Modifier
            .padding(10.dp),
            contentAlignment = Alignment.Center) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                Column(modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    // ProfilePicture
                    Box(modifier = Modifier
                        .size(100.dp)
                        .wrapContentHeight()
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                        .background(Color.White)) {
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .align(Alignment.Center),
                            shape = CircleShape,
                            elevation = 2.dp,
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.female_chef),
                                contentDescription = "Profile Photo",
                                modifier = Modifier,
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start) {

                    MediumTitleText(
                        modifier = Modifier,
                        text = "UserName",
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W500
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

data class CollapsableSection(val title: String, val details: String)

const val MaterialIconDimension = 24f
@OptIn(ExperimentalPagerApi::class)
val PagerState.pageOffset: Float
    get() = this.currentPage + this.currentPageOffset
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    CallCreditScreen(onNavigateBack={}, onNavigateToAuthenticatedRoute={})
}
