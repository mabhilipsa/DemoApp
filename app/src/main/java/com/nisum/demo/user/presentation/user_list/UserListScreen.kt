package com.nisum.demo.user.presentation.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import com.nisum.demo.user.data.dto.User
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.navigation.NavController


@Composable
fun UserListRoot(navController: NavController, viewModel: UserListViewModel = hiltViewModel()) {
    var numUsers by remember { mutableStateOf(5) }

    LaunchedEffect(numUsers) {
        viewModel.getUsers(numUsers)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Enter number of users:")
        TextField(
            value = numUsers.toString(),
            onValueChange = { newValue -> numUsers = newValue.toIntOrNull() ?: 5 },
            label = { Text("Number of users") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.errorMessage != null) {
            Text("Error: ${viewModel.errorMessage}", color = Color.Red)
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.usersList) { user ->
                UserCard(user, navController)
            }
        }
    }
}

@Composable
fun UserCard(user: User, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("details/${user.name.first}")
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            /*Image(
                painter = rememberImagePainter(user.picture.thumbnail),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )*/
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${user.name.first} ${user.name.last}", style = MaterialTheme.typography.bodySmall)
                Text(user.location.city, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
