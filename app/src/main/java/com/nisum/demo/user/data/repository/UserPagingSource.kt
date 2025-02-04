package com.nisum.demo.user.data.repository


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nisum.demo.user.data.dto.User
import com.nisum.demo.user.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(
    private val apiService: ApiService,
    private val count: Int,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getUsers(count, page)
            val users = response.users

            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isNotEmpty()) page + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

