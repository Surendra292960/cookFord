package com.example.cook_ford.presentation.screens.authenticated.accounts.call_credit_screen_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.FontName

@Composable
fun CallCreditScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit) {

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp, start = 10.dp, end = 10.dp)
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start) {

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
            Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,){
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
            textAlign = TextAlign.Center
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
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(),
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

       ElevatedCard(modifier = Modifier.padding(bottom = 10.dp),
           colors = CardDefaults.cardColors(Color.White),
           elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingTooSmall),
           shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.fillMaxSize().padding(10.dp)){

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

        ElevatedCard(modifier = Modifier.padding(bottom = 10.dp), colors = CardDefaults.cardColors(Color.Green), elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingTooSmall), shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize().padding(10.dp)){

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

        ElevatedCard(modifier = Modifier.padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingTooSmall),
            shape = RoundedCornerShape(10.dp), onClick = { /*TODO*/ }) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize().padding(10.dp)){

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
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    CallCreditScreen(onNavigateBack={}, onNavigateToAuthenticatedRoute={})
}