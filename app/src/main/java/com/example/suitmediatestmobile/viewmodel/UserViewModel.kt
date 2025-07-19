package com.example.suitmediatestmobile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.suitmediatestmobile.model.User
import com.example.suitmediatestmobile.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {
    var name by mutableStateOf("")
    var palindrome by mutableStateOf("")
    var selectedUser by mutableStateOf("")
    var userList = mutableStateListOf<User>()
    var page = 1

    suspend fun loadUsers(refresh: Boolean = false) {
        if (refresh) {
            page = 1
            userList.clear()
        }
        val response = RetrofitClient.apiService.getUsers(page, 5)
        userList.addAll(response.data)
        page++
    }

    fun isPalindrome(): Boolean {
        val clean = palindrome.replace(" ", "").lowercase()
        return clean == clean.reversed()
    }
}
