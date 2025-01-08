package com.example.cook_ford.data

import com.example.cook_ford.data.ApiConstants.CUISINES_END_POINT
import com.example.cook_ford.data.ApiConstants.LANGUAGES_END_POINT
import com.example.cook_ford.data.ApiConstants.PROFILE_DETAILS_END_POINT
import com.example.cook_ford.data.ApiConstants.PROFILE_END_POINT
import com.example.cook_ford.data.ApiConstants.SIGN_IN_END_POINT
import com.example.cook_ford.data.ApiConstants.SIGN_UP_END_POINT
import com.example.cook_ford.data.ApiConstants.TIME_SLOTS_END_POINT
import com.example.cook_ford.data.remote.CuisineResponse
import com.example.cook_ford.data.remote.LanguagesResponse
import com.example.cook_ford.data.remote.TimeSlotsResponse
import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_request.SignUpRequest
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.data.remote.auth_response.SignUpResponse
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(SIGN_IN_END_POINT)
    suspend fun makeSignInRequest(
        @Body signInRequest: SignInRequest
    ) : Response<SignInResponse>


    @POST(SIGN_UP_END_POINT)
    suspend fun makeSignUpRequest(
        @Body signUpRequest: SignUpRequest
    ) : Response<SignUpResponse>

    @GET(PROFILE_END_POINT)
    suspend fun getProfileRequest() : Response<List<ProfileResponse>>


    @GET(PROFILE_DETAILS_END_POINT)
    suspend fun getProfileById(
        @Path("id") profileId: String
    ) : Response<ProfileResponse>

    @GET(TIME_SLOTS_END_POINT)
    suspend fun getTimeSlots() : Response<TimeSlotsResponse>

    @GET(CUISINES_END_POINT)
    suspend fun getCuisines() : Response<CuisineResponse>

    @GET(LANGUAGES_END_POINT)
    suspend fun getLanguages() : Response<LanguagesResponse>

}