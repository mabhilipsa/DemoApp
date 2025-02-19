package com.nisum.demo.user.presentation.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.google.gson.Gson
import com.nisum.demo.user.data.dto.User
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun navigateToUserDetails(navController: NavController, user: User) {
    val userJson = Gson().toJson(user)
    val encodedUserJson = URLEncoder.encode(userJson, StandardCharsets.UTF_8.toString())
    navController.navigate("userDetails/$encodedUserJson")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(navController: NavController, viewModel: UserListViewModel = hiltViewModel()) {
    var input by rememberSaveable { mutableStateOf("") }
    val users by viewModel.users.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(2.dp)
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Enter number of users") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                val count = input.toIntOrNull() ?: 0
                if (count > 0) viewModel.getUsers(count)
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Fetch Users")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyColumn {
                    items(users) { user ->
                        UserCard(user) {
                            navigateToUserDetails(navController, user)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(user: User, onUserClick: (User) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onUserClick(user) },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = "Profile com.nisum.demo.user.data.dto.Picture",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${user.name.first} ${user.name.last}")
                Text("${user.location.city}, ${user.location.country}")
            }
        }
    }
}
