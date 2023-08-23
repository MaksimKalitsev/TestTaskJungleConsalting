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
import ua.zp.testtaskjungleconsalting.data.models.RepositoryItem
import ua.zp.testtaskjungleconsalting.repository.UsersRepository
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(private val repository: UsersRepository) :
    ViewModel() {

    val isLoading = mutableStateOf(true)

    private val _reposListState = MutableStateFlow<List<RepositoryItem>>(emptyList())
    val reposListState = _reposListState.asStateFlow()

    var repo by mutableStateOf<RepositoryItem?>(null)
        private set

    fun fetchRepos(login: String){
        viewModelScope.launch {
            isLoading.value = false
            val response = repository.getRepos(login)
            _reposListState.value = response.map { it.toRepos() }
        }
    }

    fun addRepo(newRepo: RepositoryItem) {
        repo = newRepo
    }
}