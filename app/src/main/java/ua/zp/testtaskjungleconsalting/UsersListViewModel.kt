package ua.zp.testtaskjungleconsalting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import ua.zp.testtaskjungleconsalting.data.db.UserEntity
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    pager: Pager<Int, UserEntity>
) : ViewModel() {


    val userPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toUser() }
        }
        .cachedIn(viewModelScope)

}