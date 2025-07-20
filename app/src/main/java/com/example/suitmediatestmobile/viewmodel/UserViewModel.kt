package com.example.suitmediatestmobile.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatestmobile.model.User
import com.example.suitmediatestmobile.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _palindrome = MutableStateFlow("")
    val palindrome = _palindrome.asStateFlow()

    private val _selectedUser = MutableStateFlow("")
    val selectedUser = _selectedUser.asStateFlow()

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    private var page = 1

    suspend fun loadUsers(refresh: Boolean = false) {
        if (refresh) {
            page = 1
            _userList.value = emptyList()
        }
        val response = RetrofitClient.apiService.getUsers(page, 10)
        _userList.value = _userList.value + response.data
        page++
    }

    fun updateName(value: String) {
        _name.value = value
    }

    fun updatePalindrome(value: String) {
        _palindrome.value = value
    }

    fun updateSelectedUser(name: String) {
        _selectedUser.value = name
    }

    fun isPalindrome(): Boolean {
        val clean = _palindrome.value.replace(" ", "").lowercase()
        return clean == clean.reversed()
    }
}
