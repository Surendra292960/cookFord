package com.example.cook_ford.presentation.screens.authenticated.accounts.call_credit_screen_component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.theme.AppTheme
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

    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(30.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
            Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier .padding(start = 10.dp, end = 10.dp)){
            Icon(Icons.Filled.VerifiedUser, contentDescription = null, tint = Color.Green)
            Text(
                text = "Exclusive Support for 15 days!",
                style = MaterialTheme.typography.subtitle2,
                fontFamily = FontName,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }

        Text(
            text = "Experience peace of mind with our hassle-free all credit refund policy!",
            style = MaterialTheme.typography.subtitle2,
            fontFamily = FontName,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier .padding(start = 10.dp, end = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Easy refund when cook is ")
                }
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append("not reachable")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" or ")
                }
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append("unavailable for the selected locality")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" or ")
                }
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append("for the mentioned time.")
                }
            },
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
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

        Spacer(modifier = Modifier.height(10.dp))

        ElevatedCard(modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp), colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier
                   .fillMaxSize()
                   .padding(20.dp)){

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
                          text = "Rs. 1499 ",
                          style = MaterialTheme.typography.subtitle2,
                          fontFamily = FontName,
                          fontSize = 14.sp,
                          fontWeight = FontWeight.Normal,
                          color = Color.Gray,
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

        Spacer(modifier = Modifier.height(10.dp))

        ElevatedCard(modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp), colors = CardDefaults.cardColors(Color.Green), elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)){

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
                            text = "Rs. 1049 ",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
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

        Spacer(modifier = Modifier.height(10.dp))

        ElevatedCard(modifier = Modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp), colors = CardDefaults.cardColors(Color.White),elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)){

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
                            text = "Rs. 749 ",
                            style = MaterialTheme.typography.subtitle2,
                            fontFamily = FontName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
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

        Spacer(modifier = Modifier.height(20.dp))

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
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ContentView() {

    val list = listOf(R.drawable.fstep_one, R.drawable.male_chef, R.drawable.female_chef, R.drawable.ic_chef_round,R.drawable.fstep_three,)

    val pagerState: PagerState = rememberPagerState(initialPage = list.size)

    Text(
        text = "Customer Reviews",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
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
                modifier =  Modifier
                    .background(Color.Gray)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(item).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                    }
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(10.dp)){

                Image(
                    painter = painterResource(id = item),
                    contentDescription = "Image $item",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
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
            modifier = Modifier.fillMaxSize().align(Alignment.Center)
        )
    }
}


@Composable
fun CollapsableLazyColumn(sections: List<CollapsableSection>, modifier: Modifier = Modifier) {

    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }

    Text(
        text = "FAQs",
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        )

    Spacer(modifier = Modifier.height(10.dp))

    Column( modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]

            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding( vertical = 10.dp)
                    .clickable {
                        collapsedState[i] = !collapsed
                    }) {

                Text(
                    dataItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
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
                    tint = Color.LightGray,
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
