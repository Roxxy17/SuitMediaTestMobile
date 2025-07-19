package com.example.suitmediatestmobile.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://reqres.in/api/"
    private const val API_KEY = "reqres-free-v1"

    // Tambahkan interceptor untuk menambahkan header API key
    private val apiKeyInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val requestWithApiKey = originalRequest.newBuilder()
            .addHeader("x-api-key", API_KEY)
            .build()
        chain.proceed(requestWithApiKey)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
