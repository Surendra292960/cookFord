package com.example.cook_ford.data.remote

import com.example.cook_ford.data.local.SessionConstant
import com.example.cook_ford.data.local.UserSession
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ConnectivityInterceptor @Inject constructor(private val session: UserSession) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = session.getString(SessionConstant.ACCESS_TOKEN) // Fetch token from secure storage

        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token") // Add token
            .method(originalRequest.method, originalRequest.body)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
