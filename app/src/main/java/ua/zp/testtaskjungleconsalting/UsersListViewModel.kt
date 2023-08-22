package ua.zp.testtaskjungleconsalting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ua.zp.testtaskjungleconsalting.data.network.Api
import ua.zp.testtaskjungleconsalting.data.network.responses.UsersListResponse
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(private val api: Api): ViewModel() {

    private val userListState = MutableStateFlow<List<UsersListResponse>>(emptyList())

    private fun fetchUsers(){
        viewModelScope.launch {
            try {
                val response = api.getListUsers()
                userListState.value = listOf(response)
            }catch (e:Exception){

            }
        }
    }
}