package com.nisum.demo.user.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nisum.demo.user.data.dto.User
import com.nisum.demo.user.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
) {
    fun getUsers(count: Int): Flow<PagingData<User>> {
        return Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(apiService, count) }).flow
    }
}

