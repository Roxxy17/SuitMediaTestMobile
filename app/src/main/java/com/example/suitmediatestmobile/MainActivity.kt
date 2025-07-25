package com.example.suitmediatestmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.suitmediatestmobile.screens.FirstScreen
import com.example.suitmediatestmobile.screens.SecondScreen
import com.example.suitmediatestmobile.screens.ThirdScreen
import com.example.suitmediatestmobile.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationHost()
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "first") {
        composable("first") {
            FirstScreen(navController = navController, viewModel = userViewModel)
        }
        composable("second") {
            SecondScreen(navController = navController, viewModel = userViewModel)
        }
        composable("third") {
            ThirdScreen(navController = navController, viewModel = userViewModel)
        }
    }
}
