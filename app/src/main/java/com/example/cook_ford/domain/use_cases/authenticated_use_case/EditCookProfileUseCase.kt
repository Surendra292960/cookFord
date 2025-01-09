package com.example.cook_ford.domain.use_cases.authenticated_use_case

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
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    jobTypeErrorState = cook_jobTypeEmptyErrorState
                )
            )
            return false
        }

        //City Not Selected
        if (city.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    cityErrorState = cook_cityEmptyErrorState
                )
            )
            return false
        }

        // userName empty
        if (userName.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    usernameErrorState = cook_nameEmptyErrorState
                )
            )
            return false
        }
        // Phone empty
        if (phone.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    phoneErrorState = cook_phoneEmptyErrorState
                )
            )
            return false
        }
        // Alternate Phone Empty
        if (alternatePhone.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    alternatePhoneErrorState = cook_alternate_phoneEmptyErrorState
                )
            )
            return false
        }

        //Date Of Birth Not Selected
        if (dob.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    dobErrorState = cook_dobEmptyErrorState
                )
            )
        }

        //Address empty
        if (address.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    addressErrorState = cook_addressEmptyErrorState
                )
            )
        }

        //Religion empty
        if (religion.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    religionErrorState = cook_religionEmptyErrorState
                )
            )
        }


        //Experience empty
        if (experience.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    experienceErrorState = cook_experienceEmptyErrorState
                )
            )
        }

        //Salary empty
        if (salary.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    salaryErrorState = cook_salaryEmptyErrorState
                )
            )
        }

        //Salary empty
        if (numberOfVisit.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    numberOfVisitErrorState = cook_numberOfVisitEmptyErrorState
                )
            )
        }

        //FoodType empty
        if (foodType.isEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    foodTypeErrorState = cook_foodTypeEmptyErrorState
                )
            )
        }


        //cuisine empty
        if (cuisine.isNullOrEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    cuisinesErrorState = cook_cuisineEmptyErrorState
                )
            )
        }


        //Language empty
        if (languages.isNullOrEmpty()) {
            editCookProfileState.value = editCookProfileState.value.copy(
                errorState = EditCookProfileErrorState(
                    languagesErrorState = cook_languageEmptyErrorState
                )
            )
        }


        //Gender Not Selected
        if (gender.isEmpty()) {
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