package com.example.suitmediatestmobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.suitmediatestmobile.viewmodel.UserViewModel

@Composable
fun SecondScreen(navController: NavController, viewModel: UserViewModel) {
    val name by viewModel.name.collectAsState()
    val selectedUser by viewModel.selectedUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color.Cyan, Color.Blue)))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome", fontWeight = FontWeight.Light, color = Color.White)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (name.isNotEmpty()) name else "Your Name",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (selectedUser.isNotEmpty()) selectedUser else "Selected User Name",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("third") }) {
            Text("Choose a User")
        }
    }
}
