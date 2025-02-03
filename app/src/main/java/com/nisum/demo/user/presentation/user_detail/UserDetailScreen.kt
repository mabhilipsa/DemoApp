package com.nisum.demo.user.presentation.user_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nisum.demo.user.data.dto.User

@Composable
fun DetailsScreen(user: User) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("${user.name.first} ${user.name.last}", style = MaterialTheme.typography.headlineMedium)
        /*Image(
            painter = rememberImagePainter(user.picture.large),
            contentDescription = "Profile Picture",
            modifier = Modifier.fillMaxWidth()
        )*/
        Text("Age: ${user.dob.age}")
        Text("Email: ${user.email}")
        Text("Phone: ${user.phone}")
        Text("Location: ${user.location.city}, ${user.location.country}")
    }
}


