package com.example.cook_ford.presentation.screens.authenticated.accounts.call_credit_screen_component

import com.example.cook_ford.R


data class KidsData(

          val title :String,
          val rating : Float,
          val desc :String,
          val imgUri:Int
)

/**
 * create Kids List
 * */

val kidsList = listOf(
    KidsData(
        "Sitting Pretty",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.chef_cooking
    ),
    KidsData(
        "Love her Expression",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_chef_round
    ),
    KidsData(
        "Childhood Superman",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.slide_2
    ),
    KidsData(
        "Candle Night At Marina",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.male_chef
    ),
    KidsData(
        "Girl with Beautiful smile",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.female_chef
    ),
    KidsData(
        "Bath Time",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.phone_verification
    )

)