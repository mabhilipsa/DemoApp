package com.nisum.demo.app.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.nisum.demo.user.data.dto.User
import com.nisum.demo.user.presentation.user_list.UserDetailsScreen
import com.nisum.demo.user.presentation.user_list.UserListScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.UserList.route) {
        composable(Screen.UserList.route) {
            UserListScreen { user ->
                val userJson = Gson().toJson(user)
                var encode = URLEncoder.encode(userJson, StandardCharsets.UTF_8.toString())
                navController.navigate("${Screen.UserDetails.route}/$encode")
            }
        }
        composable("${Screen.UserDetails.route}/{user}") { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")
            val user = Gson().fromJson(userJson, User::class.java)
            UserDetailsScreen(user)
        }
    }
}


