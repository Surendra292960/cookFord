package com.example.cook_ford.utils
import androidx.datastore.preferences.core.stringPreferencesKey
import java.util.regex.Pattern

object AppConstants {

    //const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    val PASSWORD_PATTERN: Pattern? = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{4,}" +  // at least 4 characters
                "$"
    )
    const val ZERO = 0
    const val EMPTY_STRING = ""
    const val PROFILE_ID = "profileId"
    const val PLEASE_CHECK_INTERNET = "Please check your network connection"
   // const val AUTH_PREFERENCES = "AUTH_PREF"
    const val ERROR = "error"
    val AUTH_KEY = stringPreferencesKey("auth_key")

    //SignIn Const
    const val EMAIL = "Email"
    const val EMAIL_PLACEHOLDER = "Enter Email"
    const val PASSWORD = "Password"
    const val PASSWORD_PLACEHOLDER = "Enter Email"

    //SignUp Const
    const val PHONE = "Phone Number"
    const val PHONE_PLACEHOLDER = "Enter Phone Number"
    const val NAME = "Name"
    const val NAME_PLACEHOLDER = "Enter Full Name"
    const val CONFIRM_PASSWORD = "Confirm Password"
    const val CONFIRM_PASSWORD_PLACEHOLDER = "Confirm Password"

    //Cook SignUp Const
    const val COOK_NAME = "Cook`s Name"
    const val COOK_NAME_PLACEHOLDER = "Enter Cook Name"
    const val COOK_PHONE = "Phone Number"
    const val COOK_PHONE_PLACEHOLDER = "Enter Phone Number"
    const val COOK_ALTERNATE_PHONE = "Alternate Phone Number"
    const val COOK_ALTERNATE_PHONE_PLACEHOLDER = "Enter Alternate Phone Number"

    //HomeScreen
    const val HOME = "HOME"
    const val SEARCH = "SEARCH"
    const val PROFILE = "PROFILE"

    //ReviewScreen
    const val REVIEW = "Review"
    const val REVIEW_PLACEHOLDER = "Write Your Review"

    //ReportScreen
    const val REPORT = "Add Report"
    const val REPORT_PLACEHOLDER = "Add more details"

    //NoteScreen
    const val NOTE = "Add Note"
    const val NOTE_PLACEHOLDER = "Any important details you want to remember about the cook (Only visible to you)"

     //Account
     const val ACCOUNT_NAVIGATION = "Account"
     const val PROFILE_ACCOUNT = "ProfileAccount"
     const val CALL_CREDIT = "CallCredit"
     const val EDIT_PROFILE = "Edit Profile"
     const val ADD_COOK_PROFILE = "AddCookProfile"
     const val POST_JOB = "PostJob"
     const val COOK_PREFERENCES = "CookPreferences"
}