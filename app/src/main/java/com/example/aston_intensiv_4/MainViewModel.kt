package com.example.aston_intensiv_4

import androidx.lifecycle.ViewModel
import com.example.aston_intensiv_4.task2.User
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(
        listOf(
            User(1, R.drawable.user1, "John", "Doe", "+1234567890"),
            User(2, R.drawable.user2, "Jane", "Smith", "+0987654321"),
            User(3, R.drawable.user3, "Alice", "Johnson", "+1122334455"),
            User(4, R.drawable.user4, "Bob", "Brown", "+2233445566")
        )
    )
    val userList: MutableStateFlow<List<User>> get() = _userList

    fun updateUser(updatedUser: User) {
        val currentList = _userList.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            currentList[index] = updatedUser
            _userList.value = currentList
        }
    }
}