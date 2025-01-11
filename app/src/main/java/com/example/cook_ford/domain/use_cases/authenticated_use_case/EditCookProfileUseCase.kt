package com.example.cook_ford.domain.use_cases.authenticated_use_case

import android.util.Log
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.EditCookProfileState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_addressEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_alternate_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_cityEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_cuisineEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_dobEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_experienceEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_foodTypeEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_jobTypeEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_languageEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_nameEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_numberOfVisitEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_phoneEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_religionEmptyErrorState
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.state.cook_salaryEmptyErrorState
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.state.genderSelectionErrorState
import com.example.cook_ford.utils.AppConstants
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class EditCookProfileUseCase  @Inject constructor(private val repository: AuthRepositoryImpl)  {

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (use case)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    operator fun invoke(editCookProfileState: MutableStateFlow<EditCookProfileState>): Boolean {
        val userName = editCookProfileState.value.username.trim()
        val phone = editCookProfileState.value.phone.trim()
        val alternatePhone = editCookProfileState.value.alternatePhone.trim()
        val gender = editCookProfileState.value.gender.trim()
        val city = editCookProfileState.value.city.trim()
        val profileImage = editCookProfileState.value.profileImage.trim()
        val jobType = editCookProfileState.value.jobType
        val workArea = editCookProfileState.value.workArea.trim()
        val address = editCookProfileState.value.address.trim()
        val religion = editCookProfileState.value.religion.trim()
        val experience = editCookProfileState.value.experience.trim()
        val dob = editCookProfileState.value.dob.trim()
        val salary = editCookProfileState.value.salary.trim()
        val numberOfVisit = editCookProfileState.value.numberOfVisit.trim()
        val cuisine = editCookProfileState.value.cuisine
        val languages = editCookProfileState.value.languages
        val foodType = editCookProfileState.value.foodType.trim()


        //ProfileImage Not Selected
        /* if (profileImage.isEmpty()) {
             editCookProfileState.value = editCookProfileState.value.copy(
                 errorState = AddCookProfileErrorState(
                     profileImageErrorState = cook_profileImageEmptyErrorState
                 )
             )
             return false
         }*/

        //JobType Not Selected
        if (jobType?.size == AppConstants.ZERO) {
            Log.d("TAG", "validation jobType: $jobType")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    jobTypeErrorState = cook_jobTypeEmptyErrorState
                )
            )
            return false
        }

        //City Not Selected
        if (city.isEmpty()) {
            Log.d("TAG", "validation city: $city")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    cityErrorState = cook_cityEmptyErrorState
                )
            )
            return false
        }

        // userName empty
        if (userName.isEmpty()) {
            Log.d("TAG", "validation userName: $userName")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    usernameErrorState = cook_nameEmptyErrorState
                )
            )
            return false
        }
        // Phone empty
        if (phone.isEmpty()) {
            Log.d("TAG", "validation phone: $phone")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    phoneErrorState = cook_phoneEmptyErrorState
                )
            )
            return false
        }
        // Alternate Phone Empty
        if (alternatePhone.isEmpty()) {
            Log.d("TAG", "validation alternatePhone: $alternatePhone")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    alternatePhoneErrorState = cook_alternate_phoneEmptyErrorState
                )
            )
            return false
        }

        //Date Of Birth Not Selected
        if (dob.isEmpty()) {
            Log.d("TAG", "validation dob: $dob")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    dobErrorState = cook_dobEmptyErrorState
                )
            )
            return false
        }

        //Address empty
        if (address.isEmpty()) {
            Log.d("TAG", "validation address: $address")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    addressErrorState = cook_addressEmptyErrorState
                )
            )
            return false
        }

        //Experience empty
        if (experience.isEmpty()) {
            Log.d("TAG", "validation experience: $experience")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    experienceErrorState = cook_experienceEmptyErrorState
                )
            )
            return false
        }

        //Salary empty
        if (salary.isEmpty()) {
            Log.d("TAG", "validation salary: $salary")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    salaryErrorState = cook_salaryEmptyErrorState
                )
            )
            return false
        }

        //Religion empty
        if (religion.isEmpty()) {
            Log.d("TAG", "validation religion: $religion")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    religionErrorState = cook_religionEmptyErrorState
                )
            )
            return false
        }

        //cuisine empty
        if (cuisine.isNullOrEmpty()) {
            Log.d("TAG", "validation cuisine: $cuisine")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    cuisinesErrorState = cook_cuisineEmptyErrorState
                )
            )
            return false
        }


        //Language empty
        if (languages.isNullOrEmpty()) {
            Log.d("TAG", "validation languages: $languages")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    languagesErrorState = cook_languageEmptyErrorState
                )
            )
            return false
        }

        //Visit empty
        if (numberOfVisit.isEmpty()) {
            Log.d("TAG", "validation numberOfVisit: $numberOfVisit")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    numberOfVisitErrorState = cook_numberOfVisitEmptyErrorState
                )
            )
            return false
        }

        //FoodType empty
        if (foodType.isEmpty()) {
            Log.d("TAG", "validation foodType: $foodType")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    foodTypeErrorState = cook_foodTypeEmptyErrorState
                )
            )
            return false
        }


        //Gender Not Selected
        if (gender.isEmpty()) {
            Log.d("TAG", "validation gender: $gender")
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    genderErrorState = genderSelectionErrorState
                )
            )
            return false
        }

        // No errors
        else {
            // Set default error state
            editCookProfileState.value = editCookProfileState.value.copy(errorState = EditCookProfileErrorState())
            return true
        }
    }
}