package com.example.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HMPizzaRetrofitInterceptor(
    private val appId: String,
    private val appKey: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val url = originalRequest.url().newBuilder()
            .addQueryParameter("app_id", appId)
            .addQueryParameter("app_key", appKey)
            .build()

        val newRequest = originalRequest.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}