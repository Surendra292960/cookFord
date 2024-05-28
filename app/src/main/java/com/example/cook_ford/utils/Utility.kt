package com.example.cook_ford.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.cook_ford.presentation.route.bottom_nav.NavTitle
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


object Utility {

    fun getDays(updatedAt:String):String{
        var days:String = AppConstants.EMPTY_STRING
        val toyBornTime = "2024-05-14T21:48:02.296Z"
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val oldDate: Date = format.parse(updatedAt)!!
            println(oldDate)

            val currentDate = Date()

            val diff: Long = currentDate.getTime() - oldDate.getTime()
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            days = (hours / 24).toString()

            if (oldDate.before(currentDate)) {
                Log.e("oldDate", "is previous date")
                Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes + " hours: " + hours + " days: " + days)
            }

            // Log.e("toyBornTime", "" + toyBornTime);
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return days
    }


    fun shareProfile(context: Context) {
        // Get the file to share
        /*val file = File(context.cacheDir, "profile.txt")
        file.writeText("This is my profile information.")

        // Create a content URI for the file
        val uri = FileProvider.getUriForFile(context, "com.example.my.app.fileprovider", file)
    */
        // Create the share intent
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, "uri")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        // Start the share activity
        context.startActivity(Intent.createChooser(intent, "Share Profile"))
    }

    fun getTitleByRoute(route:String): String {
        return when (route) {
            AppConstants.HOME -> NavTitle.HOME
            AppConstants.SEARCH -> NavTitle.SEARCH
            AppConstants.PROFILE -> NavTitle.PROFILE
            AppConstants.REVIEW -> NavTitle.REVIEW
            // other cases
            else -> NavTitle.HOME
        }
    }
}

