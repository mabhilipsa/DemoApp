package com.nisum.demo.user.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisum.demo.user.data.dto.User
import com.nisum.demo.user.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var usersList = mutableListOf<User>()
    var errorMessage: String? = null

    fun getUsers(results: Int) {
        viewModelScope.launch {
            try {
                val response = userRepository.getUsers(results)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        usersList = response.body()?.results?.toMutableList() ?: mutableListOf()
                    } else {
                        errorMessage = "Error: ${response.message()}"
                    }
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.localizedMessage}"
            }
        }
    }
}


