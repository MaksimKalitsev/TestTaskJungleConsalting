package ua.zp.testtaskjungleconsalting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.zp.testtaskjungleconsalting.data.models.Users
import ua.zp.testtaskjungleconsalting.repository.UsersRepository
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(private val repository: UsersRepository) :
    ViewModel() {

    val isLoading = mutableStateOf(true)
    private val _userListState = MutableStateFlow<List<Users>>(emptyList())
    val userListState = _userListState.asStateFlow()
    //Detail Screen variable
    var user by mutableStateOf<Users?>(null)
        private set

    fun fetchUsers() {
        viewModelScope.launch {
            isLoading.value = false
            val response = repository.getUsers()
            _userListState.value = response.map { it.toUsers() }

        }
    }
    fun addUser(newUser: Users) {
        user = newUser
    }
}