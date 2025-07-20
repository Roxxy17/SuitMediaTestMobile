package com.example.suitmediatestmobile.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.suitmediatestmobile.viewmodel.UserViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun ThirdScreen(navController: NavController, viewModel: UserViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isRefreshing = remember { mutableStateOf(false) }

    val userList by viewModel.userList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing.value),
        onRefresh = {
            scope.launch {
                isRefreshing.value = true
                viewModel.loadUsers(refresh = true)
                isRefreshing.value = false
            }
        }
    ) {
        LazyColumn {
            items(userList) { user ->
                ListItem(
                    headlineContent = { Text("${user.first_name} ${user.last_name}") },
                    supportingContent = { Text(user.email) },
                    leadingContent = {
                        AsyncImage(
                            model = user.avatar,
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                    },
                    modifier = Modifier.clickable {
                        viewModel.updateSelectedUser("${user.first_name} ${user.last_name}")
                        navController.popBackStack()
                    }
                )
                Divider()
            }
        }
    }
}

