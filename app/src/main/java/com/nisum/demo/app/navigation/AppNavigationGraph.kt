package com.nisum.demo.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nisum.demo.user.presentation.Screen
import com.nisum.demo.user.presentation.user_detail.DetailsScreen
import com.nisum.demo.user.presentation.user_list.UserListRoot
import com.nisum.demo.user.presentation.user_list.UserListViewModel

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.USER_LIST) {
        composable(route = Routes.USER_LIST) {
            UserListRoot(navController)
        }
        composable("details/{userName}") { backStackEntry ->
            val viewModel = hiltViewModel<UserListViewModel>()
            val userName = backStackEntry.arguments?.getString("userName")
            val user = viewModel.usersList.first { it.name.first == userName }
            DetailsScreen(user)
        }
    }


}