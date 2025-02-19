package com.nisum.demo.user.data.repository

import com.nisum.demo.user.data.dto.User
import com.nisum.demo.user.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val api: ApiService) {
    suspend fun fetchUsers(count: Int): List<User> {
        return api.getUsers(count).results
    }
}


