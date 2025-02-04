package com.nisum.demo.user.presentation.user_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nisum.demo.user.data.dto.User
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage

@Composable
fun UserListScreen(viewModel: UserListViewModel = hiltViewModel(), onUserClick: (User) -> Unit) {
    var count by remember { mutableStateOf("") }
    val context = LocalContext.current
    val users = viewModel.users.collectAsLazyPagingItems()
    val isLoading by viewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = count,
            onValueChange = { count = it },
            label = { Text("Enter number of users") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (count.isNotEmpty()) {
                    viewModel.fetchUsers(count.toInt())
                } else {
                    Toast.makeText(context, "Enter a number", Toast.LENGTH_SHORT).show()
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Users")
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(users.itemCount) { index ->
                    users[index]?.let { user ->
                        UserCard(user = user, onClick = { onUserClick(user) })
                    }
                }
            }
        }
    }
}

@Composable
fun UserCard(user: User, onClick: (User) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick(user) }

    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Log.d("ImageURL", "User Image URL: ${user.picture.large}")
            AsyncImage(
                model = user.picture.medium,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Name: ${user.name.first} ${user.name.last}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Address: ${user.location.street.name}, ${user.location.city}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


