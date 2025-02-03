package com.nisum.demo.user.data.repository


import com.nisum.demo.user.data.dto.RandomUserResponseDto
import com.nisum.demo.user.data.network.UserApi
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun getUsers(results: Int): Response<RandomUserResponseDto> {
        return userApi.getRandomUsers(results)
    }
}
