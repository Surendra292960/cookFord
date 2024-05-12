package com.example.cook_ford.presentation.screens.onboard
import com.example.cook_ford.R

class OnBoardingItems(val image: Int, val title: Int, val desc: Int) {
    companion object{
        fun getData(): List<OnBoardingItems>{
            return listOf(
                OnBoardingItems(R.drawable.slide_1, R.string.onbaord_title1, R.string.onboard_text1),
                OnBoardingItems(R.drawable.slide_2, R.string.onbaord_title2, R.string.onboard_text2),
                OnBoardingItems(R.drawable.slide_3, R.string.onbaord_title3, R.string.onboard_text3)
            )
        }
    }
}