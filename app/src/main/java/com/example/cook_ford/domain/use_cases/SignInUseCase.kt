package com.example.cook_ford.domain.use_cases

import com.example.cook_ford.data.remote.auth_request.SignInRequest
import com.example.cook_ford.data.remote.auth_response.SignInResponse
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import com.example.cook_ford.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    
    suspend operator fun invoke(signInRequest: SignInRequest): Flow<NetworkResult<SignInResponse>> {
        return repository.signIn(signInRequest)
    }
}