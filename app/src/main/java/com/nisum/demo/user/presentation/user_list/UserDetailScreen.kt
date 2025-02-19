import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.google.gson.Gson
import com.nisum.demo.user.data.dto.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val userJson = backStackEntry.arguments?.getString("user") ?: return
    val user = Gson().fromJson(Uri.decode(userJson), User::class.java)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = "Profile com.nisum.demo.user.data.dto.Picture",
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Name: ${user.name.first} ${user.name.last}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Email: ${user.email}", fontSize = 16.sp)
            Text("Phone: ${user.phone}", fontSize = 16.sp)
            Text("Address: ${user.location.street.name}, ${user.location.city}, ${user.location.country}", fontSize = 16.sp)
        }
    }
}

