package com.example.cook_ford.presentation.common.user_list_card

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.cook_ford.R

data class ProductModel(
    val title: String,
    val price: String,
    @DrawableRes
    val image: Int,
    val color: Color
)

fun getData(): List<ProductModel> {
    return listOf(
        ProductModel("Sonu 1","$223.00", R.drawable.slide_1, Color(0xFFFF1A9B)),
        ProductModel("Vikas","$223.00", R.drawable.slide_2, Color(0xFF5C00FF)),
        ProductModel("Vijay","$223.00", R.drawable.ic_bubble, Color(0xFFFF0000)),
        ProductModel("Sethy","$223.00", R.drawable.fstep_one, Color(0xFF447ACE)),
        ProductModel("Gagan","$223.00", R.drawable.fstep_two, Color(0xFFFF1A9B)),
        ProductModel("Logan","$223.00", R.drawable.fstep_three, Color(0xFF5C00FF)),
        ProductModel("Kumar","$223.00", R.drawable.ic_headphone, Color(0xFFFF0000)),
        ProductModel("Chintu","$223.00", R.drawable.ic_videocam, Color(0xFF447ACE)),
        ProductModel("Sonu 1","$223.00", R.drawable.slide_1, Color(0xFFFF1A9B)),
        ProductModel("Vikas","$223.00", R.drawable.slide_2, Color(0xFF5C00FF)),
        ProductModel("Vijay","$223.00", R.drawable.ic_bubble, Color(0xFFFF0000)),
        ProductModel("Sethy","$223.00", R.drawable.fstep_one, Color(0xFF447ACE)),
        ProductModel("Gagan","$223.00", R.drawable.fstep_two, Color(0xFFFF1A9B)),
        ProductModel("Logan","$223.00", R.drawable.fstep_three, Color(0xFF5C00FF)),
        ProductModel("Kumar","$223.00", R.drawable.ic_headphone, Color(0xFFFF0000)),
        ProductModel("Chintu","$223.00", R.drawable.ic_videocam, Color(0xFF447ACE)),
    )
}