package com.example.suitmediatestmobile.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.suitmediatestmobile.viewmodel.UserViewModel

@Composable
fun FirstScreen(navController: NavController, viewModel: UserViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color.Blue, Color.Green))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Name") }
        )
        OutlinedTextField(
            value = viewModel.palindrome,
            onValueChange = { viewModel.palindrome = it },
            label = { Text("Palindrome") }
        )

        Button(onClick = {
            val result = if (viewModel.isPalindrome()) "isPalindrome" else "not palindrome"
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }) {
            Text("Check")
        }

        Button(onClick = { navController.navigate("second") }) {
            Text("Next")
        }
    }
}
