package com.example.cook_ford.domain.use_cases

import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: AuthRepositoryImpl) {
    suspend operator fun invoke(): Flow<NetworkResult<List<ProfileResponse>>> {
        return repository.getProfile()
    }
    suspend operator fun invoke(profileId: String): Flow<NetworkResult<ProfileResponse>> {
        return repository.getProfileById(profileId)
    }
}