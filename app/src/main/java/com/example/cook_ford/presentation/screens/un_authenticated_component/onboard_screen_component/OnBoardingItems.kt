package com.example.cook_ford.presentation.screens.un_authenticated_component.onboard_screen_component
import com.example.cook_ford.R

class OnBoardingItems(val image: Int, val title: Int, val desc: Int) {
    companion object{
        fun getData(): List<OnBoardingItems>{
            return listOf(
                OnBoardingItems(R.drawable.lifestyle_male_cooking_onboard, R.string.onbaord_title1, R.string.onboard_text1),
                OnBoardingItems(R.drawable.female_cooking_onboard, R.string.onbaord_title2, R.string.onboard_text2),
                OnBoardingItems(R.drawable.male_cooking_onboard, R.string.onbaord_title3, R.string.onboard_text3)
            )
        }
    }
}