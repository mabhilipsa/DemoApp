package com.nisum.demo.user.data.network

import com.nisum.demo.user.data.dto.RandomUserResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface UserApi {
    @GET("api/")
    suspend fun getRandomUsers(@Query("results") results: Int): Response<RandomUserResponseDto>
}


