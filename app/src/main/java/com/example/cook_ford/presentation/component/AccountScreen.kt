import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SmallTitleText
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.presentation.theme.OrangeYellow1

data class AccountModelData( @DrawableRes val leadingIcon: Int,  @DrawableRes val trailingIcon: Int, val isBorder:Boolean = false, val title: String, val subTitle: String)

val accountData = listOf(
    AccountModelData(
        leadingIcon = R.drawable.post_job_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Post Job",
        subTitle = "Get notification when job posted"
    ),
    AccountModelData(
        leadingIcon = R.drawable.profile_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Add Your Cook",
        subTitle = ""
    ),
    AccountModelData(
        leadingIcon = R.drawable.community_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Tell Your Community",
        subTitle = ""
    ),
    AccountModelData(
        leadingIcon = R.drawable.contact_us_icon,
        trailingIcon = R.drawable.ic_email,
        isBorder = true,
        title = "Contact Us",
        subTitle = ""
    ),
    AccountModelData(
        leadingIcon = R.drawable.review_us_icon,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Review Us",
        subTitle = "We are always happy to hear from you"
    ),
    AccountModelData(
        leadingIcon = R.drawable.cook_ford_rounded_logo,
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = false,
        title = "Sign In as Cook",
        subTitle = "you will be signed out from this account"
    )
)


@Composable
fun ProfileScreen() {
   // val state by viewModel.state.collectAsState()
    ProfileContent(
      //  state = state,
        onChangeFirstName = {},
        onChangeLastName = {},
        onChangeLocation = {},
        onChangeEmail = {},
        onChangePhone = {},
        onSaveUserInfo = {}
    )
}

@Composable
private fun ProfileContent(
   // state: ProfileUiState,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeLocation: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangePhone: (String) -> Unit,
    onSaveUserInfo: () -> Unit) {

    val changeProfileState = remember { mutableStateOf("Male") }

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
       val profileLazyListState: LazyListState = rememberLazyListState()

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier
            .clickable {},
            contentAlignment = Alignment.Center) {
            ProfileImage(changeProfileState, onChange = {})
            Image(modifier = Modifier.padding(AppTheme.dimens.paddingNormal)
                .size(30.dp).align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_edit_icon),
                contentDescription = "Edit Profile")
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimens.paddingSmall),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Spacer(modifier = Modifier.width(5.dp))
            TextButton(
                colors = ButtonDefaults
                    .textButtonColors(
                        backgroundColor = OrangeYellow1,
                        contentColor = Color.White
                    ),
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                MediumTitleText(
                    modifier = Modifier,
                    text = "0  Call Credit",
                    fontWeight = FontWeight.W900,
                    textAlign = TextAlign.Center,
                    textColor = Color.White
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            TextButton(
                colors = ButtonDefaults
                    .textButtonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.White
                    ),
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                MediumTitleText(
                    modifier = Modifier,
                    text = "Call Credit",
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    textColor = Color.White
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }


        ElevatedCard(modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingExtraLarge),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)){

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimens.paddingTooSmall),
                state = profileLazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
                content = {
                    items(accountData.size) { index ->
                        AccountItem(
                            accountData = accountData,
                            index = index,
                            onItemClick = {

                            }
                        )
                    }
                    item {
                        HorizontalDivider(modifier = Modifier.padding(top = 5.dp), color = Color.LightGray)
                        FooterStatus()
                    }
                }
            )
        }
    }
}

@Composable
fun AccountItem(accountData: List<AccountModelData>, index: Int, onItemClick: () -> Unit) {

    Column(modifier = Modifier.padding(AppTheme.dimens.paddingSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Box(modifier = Modifier
            .border(
                if (accountData[index].isBorder) 1.dp else (-1).dp,
                Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 10.dp)
            .fillMaxWidth()
            .clickable { }) {
            Row(modifier = Modifier
                .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Box(modifier = Modifier
                    .size(30.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .clickable {},
                    contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = accountData[index].leadingIcon),
                        contentDescription = "Facebook Login Icon"
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 15.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center) {

                    MediumTitleText(
                        modifier = Modifier.align(Alignment.Start),
                        text = accountData[index].title,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W500
                    )

                    SmallTitleText(
                        modifier = Modifier,
                        text = accountData[index].subTitle,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W400
                    )
                }
            }

            Box(modifier = Modifier
                .size(if (accountData[index].title == "Contact Us") 30.dp else 15.dp)
                .align(Alignment.CenterEnd)
                .clickable {},
                contentAlignment = Alignment.Center) {
                if (accountData[index].isBorder) {
                    Image(
                        painter = painterResource(id = accountData[index].trailingIcon),
                        contentDescription = "Icon"
                    )
                }
            }
        }
    }
}

@Composable
fun FooterStatus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        androidx.wear.compose.material.Text(
            text = "Terms of Use",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )

        androidx.wear.compose.material.Text(
            text = "Privacy Policy",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )

        androidx.wear.compose.material.Text(
            text = "License",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
