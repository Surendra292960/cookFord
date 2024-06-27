package com.example.cook_ford.presentation.component.widgets.topbar_nav
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
	TopBarNavigation(onNavigateBack = {}, title = "Home")
}
@Composable
fun TopBarNavigation(onNavigateBack: () -> Unit, title: String) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(15.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically) {
		IconButton(
			onClick = {
				onNavigateBack.invoke()
			},
			modifier = Modifier
				.background(color = Color.White, shape = CircleShape)
				.clip(CircleShape)
				.size(40.dp)

		) {
			Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "")
		}

		Text(
			text = title,
			color = Color.DarkGray,
			style = MaterialTheme.typography.subtitle2,
			fontSize = 18.sp,
			textDecoration = TextDecoration.None,
		)
		Row(
			modifier = Modifier
				.width(70.dp)
				.background(color = Color.White, shape = RoundedCornerShape(8.dp))
				.padding(3.dp)
				.clip(RoundedCornerShape(8.dp)),
			horizontalArrangement = Arrangement.spacedBy(
				4.dp,
				Alignment.CenterHorizontally
			),
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = "4.8",
				fontWeight = FontWeight.Bold,
				color = Color.DarkGray
			)
			Image(
				painter = painterResource(id = R.drawable.star_full),
				contentDescription = null,
			)
		}
	}
}