package com.example.cook_ford.presentation.screens.authenticated.profile.details.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

object ImageUtils {
    fun generateShareImage(view: View): Bitmap {
        val b = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )
        val c = Canvas(b)
        view.layout(
            view.left,
            view.top,
            view.right,
            view.bottom
        )
        view.draw(c)
        return b
    }
}