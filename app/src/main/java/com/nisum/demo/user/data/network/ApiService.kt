package com.nisum.demo.user.data.network

import com.nisum.demo.user.data.dto.RandomUserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getUsers(
        @Query("results") count: Int,
        @Query("page") page: Int,
    ): RandomUserResponseDto
}




