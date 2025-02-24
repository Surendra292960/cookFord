package com.example.cook_ford.domain.use_cases.authenticated_use_case

import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend operator fun invoke(): Flow<NetworkResult<List<ProfileResponse>>> {
        return repository.getProfiles()
    }
    suspend operator fun invoke(authToken:String, profileId: String): Flow<NetworkResult<ProfileResponse>> {
        return repository.getProfileById(authToken, profileId)
    }
    suspend operator fun invoke(authToken:String, profileId: String, data: String): Flow<NetworkResult<ProfileResponse>> {
        return repository.getProviderProfileById(authToken, profileId)
    }
}