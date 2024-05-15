package com.example.cook_ford.di
import android.content.Context
import com.example.cook_ford.data.ApiConstants.BASE_URL
import com.example.cook_ford.data.ApiService
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.data.repository.AuthRepository
import com.example.cook_ford.domain.use_cases.ProfileUseCase
import com.example.cook_ford.domain.use_cases.SignInUseCase
import com.example.cook_ford.domain.use_cases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


/*    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.createDataStore(AUTH_PREFERENCES)


    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>) =
        AuthPreferences(dataStore)*/



    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

/*    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)*/

    @Provides
    @Singleton
    fun providesAuthRepository(
        apiService: ApiService,
    ): AuthRepository {
        return AuthRepository(
            apiService = apiService
        )
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): UserSession {
        return UserSession(context)
    }
    @Provides
    @Singleton
    fun providesLoginUseCase(repository: AuthRepository): SignInUseCase {
        return SignInUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesProfileUseCase(repository: AuthRepository): ProfileUseCase {
        return ProfileUseCase(repository)
    }


      @Provides
      @Singleton
      fun providesRegisterUseCase(repository: AuthRepository): SignUpUseCase {
          return SignUpUseCase(repository)
      }

}