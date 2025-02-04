package com.nisum.demo.user.presentation.user_list

import androidx.compose.foundation.background
import com.nisum.demo.user.data.dto.User
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@ExperimentalMaterial3Api
@Composable
fun UserDetailsScreen(user: User) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Detail") },
                Modifier.background(MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->
        Spacer(modifier = Modifier.height(80.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Name: ${user.name.first} ${user.name.last}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Address: ${user.location.street.name}, ${user.location.city}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Country: ${user.location.country}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "Age: ${user.dob.age}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "email: ${user.email}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "phone: ${user.phone}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}




