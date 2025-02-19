package com.nisum.demo.app.navigation

import UserDetailsScreen
import com.nisum.demo.user.presentation.user_list.UserListScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.UserList.route) {
        composable(Screen.UserList.route) {
            UserListScreen(navController)
        }
        composable(
            "userDetails/{user}",
            arguments = listOf(navArgument("user") { type = NavType.StringType })
        ) { backStackEntry ->
            UserDetailsScreen(navController, backStackEntry)
        }
    }
}




