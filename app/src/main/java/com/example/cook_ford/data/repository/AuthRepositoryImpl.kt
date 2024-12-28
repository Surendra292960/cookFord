package com.example.cook_ford.data.repository
import com.example.cook_ford.data.ApiService
import com.example.cook_ford.data.remote.BaseRepo
import com.example.cook_ford.data.remote.NetworkResult
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl(
    private val apiService: ApiService,
    //private val preferences: AuthPreferences
) : AuthRepository, BaseRepo() {

    override suspend fun getProfiles(): Flow<NetworkResult<List<ProfileResponse>>> {
        return flow {
            emit(safeApiCall { apiService.getProfileRequest() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getProfileById(profileId: String): Flow<NetworkResult<ProfileResponse>> {
        return flow {
            emit(safeApiCall { apiService.getProfileById(profileId) })
        }.flowOn(Dispatchers.IO)
    }

}