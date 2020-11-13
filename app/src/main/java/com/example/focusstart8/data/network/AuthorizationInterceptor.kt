package com.example.focusstart8.data.network

import com.example.focusstart8.data.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Constants.BEARER_TOKEN}")
            .build()
        return chain.proceed(request)
    }
}