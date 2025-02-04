package com.nisum.demo.user.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nisum.demo.user.data.dto.User
import com.nisum.demo.user.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    private val _users = MutableStateFlow<PagingData<User>>(PagingData.empty())
    val users: StateFlow<PagingData<User>> = _users.asStateFlow()

    fun fetchUsers(count: Int) {
        _loading.value = true

        viewModelScope.launch {
            repository.getUsers(count)
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _users.value = pagingData
                    _loading.value = false
                }
        }
    }
}



