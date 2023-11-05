package com.example.hm_pizza.di.modules.dataModules

import com.example.data.retrofit.HMPizzaRetrofitInterceptor
import com.example.data.retrofit.HMPizzaRetrofitService
import com.example.hm_pizza.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitModule {
    private val baseUrl = "https://api.edamam.com"

    @Provides
    fun createClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(
            HMPizzaRetrofitInterceptor(
                BuildConfig.APPLICATION_ID,
                BuildConfig.APPLICATION_KEY
            )
        )
        .build()

    @Provides
    fun provideRetrofitService(client:OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()

    @Provides
    fun createRetrofitService(retrofit: Retrofit): HMPizzaRetrofitService =
        retrofit.create(HMPizzaRetrofitService::class.java)
}