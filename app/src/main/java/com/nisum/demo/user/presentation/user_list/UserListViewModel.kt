package com.nisum.demo.user.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.nisum.demo.user.data.dto.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisum.demo.user.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository,
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users
    private val _userInput = mutableStateOf("")
    val userInput: State<String> = _userInput

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun saveUser(user: User) {
        savedStateHandle["selectedUser"] = user
    }

    fun getUser(): User? {
        return savedStateHandle["selectedUser"]
    }

    fun updateUserInput(input: String) {
        _userInput.value = input
    }

    fun getUsers(count: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _users.value = repository.fetchUsers(count)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
