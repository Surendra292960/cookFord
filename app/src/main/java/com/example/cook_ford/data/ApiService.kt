package com.example.cook_ford.data

import com.example.cook_ford.data.ApiConstants.SIGN_IN_END_POINT
import com.example.cook_ford.data.ApiConstants.SIGN_UP_END_POINT
import com.example.cook_ford.data.remote.request.SignInRequest
import com.example.cook_ford.data.remote.request.SignUpRequest
import com.example.cook_ford.data.remote.response.SignInResponse
import com.example.cook_ford.data.remote.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(SIGN_IN_END_POINT)
    suspend fun makeSignInRequest(
        @Body signInRequest: SignInRequest
    ) : Response<SignInResponse>


    @POST(SIGN_UP_END_POINT)
    suspend fun makeSignUpRequest(
        @Body signUpRequest: SignUpRequest
    ) : Response<SignUpResponse>

    @GET("api/user/{id}")
    suspend fun getUserDetails()

}