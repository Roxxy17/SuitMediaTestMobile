package com.example.suitmediatestmobile.network

import com.example.suitmediatestmobile.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users") // tidak perlu "api/" karena baseUrl sudah sampai /api/
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserResponse
}
