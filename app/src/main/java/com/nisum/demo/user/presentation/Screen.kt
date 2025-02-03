package com.nisum.demo.user.presentation

sealed class Screen (val route : String) {
    data object UsersListScreen : Screen("users_list_screen")
}