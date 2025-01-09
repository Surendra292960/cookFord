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
    const val MESSAGE = "message"
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

    //Address Const
    const val FIRST_NAME = "FName"
    const val FIRST_NAME_PLACEHOLDER = "Enter First Name"
    const val LAST_NAME = "LName"
    const val LAST_NAME_PLACEHOLDER = "Enter Last Name"
    const val ADDRESS = "Address"
    const val ADDRESS_PLACEHOLDER  = "Enter Address"
    const val CITY = "City"
    const val CITY_PLACEHOLDER  = "Enter City"
    const val STATE = "State"
    const val STATE_PLACEHOLDER  = "Enter State"
    const val ZIP_CODE = "Zip Code"
    const val ZIP_CODE_PLACEHOLDER  = "Enter Zip Code"
    const val RELIGION = "Religion"
    const val RELIGION_PLACEHOLDER  = "Enter Religion"

    //Cook SignUp Const
    const val COOK_NAME = "Cook`s Name"
    const val COOK_NAME_PLACEHOLDER = "Enter Cook Name"
    const val COOK_PHONE = "Phone Number"
    const val COOK_PHONE_PLACEHOLDER = "Enter Phone Number"
    const val COOK_ALTERNATE_PHONE = "Alternate Phone Number"
    const val COOK_ALTERNATE_PHONE_PLACEHOLDER = "Enter Alternate Phone Number"
    const val COOK_DOB = "Date of birth"
    const val COOK_DOB_PLACEHOLDER = "Enter date of birth"
    const val COOK_ADDRESS = "where are you from?(Native place)"
    const val COOK_ADDRESS_PLACEHOLDER = "E.g Noida"
    const val COOK_EXPERIENCE = "Experience in cooking (in year)"
    const val COOK_EXPERIENCE_PLACEHOLDER = "E.g. 4"
    const val COOK_SALARY = "min monthly charges"
    const val COOK_SALARY_PLACEHOLDER = "Eg- 4000"
    const val COOK_RELIGION = "Religion"
    const val COOK_RELIGION_PLACEHOLDER = "Religion"


    //ReviewScreen
    const val LABEL_REVIEW = "Review"
    const val REVIEW_PLACEHOLDER = "Write Your Review"

    //ReportScreen
    const val LABEL_REPORT = "Add Report"
    const val REPORT_PLACEHOLDER = "Add more details"

    //NoteScreen
    const val LABEL_NOTE = "Add Note"
    const val NOTE_PLACEHOLDER = "Any important details you want to remember about the cook (Only visible to you)"

    const val AFTER_NOON = "Afternoon   "
    const val EVENING = "Evening      "
    const val MORNING = "Morning     "
    const val GENDER = "Gender"
    const val CUISINES = "Cuisines"
    const val LANGUAGES = "Languages"
    const val CHEF_READY_TO_RELOCATE = "Show chefs ready to relocate?"
    const val NO_OF_VISIT_A_DAY = "Number of visit per day"
    const val COOK_TYPE = "Cook Type"
    const val EXPERIENCED_BETWEEN = "Who is Experienced between"
    const val CALL_CREDIT = "Call Credit"
    const val BUY_CALL_CREDIT = "Buy Call Credit"
    const val REVIEW = "Review"
    const val LANGUAGE = "Language"
    const val EXPERIENCE = "Experience"
    const val FROM = "From"
    const val PERSONAL_INFO = "Add Your Personal Info Here"

   //User Type
   const val PROVIDER = "provider"
   const val USER = "user"
   const val ADD_PHOTO = "Add photo"
   const val RATING = "Rating"
   const val VIEWS = "Views"

}