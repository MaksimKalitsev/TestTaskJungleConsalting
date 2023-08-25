package ua.zp.testtaskjungleconsalting.presentation.detailsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import ua.zp.testtaskjungleconsalting.data.db.RepoEntity
import ua.zp.testtaskjungleconsalting.data.network.ReposRemoteMediator
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(
    private val remoteMediator: ReposRemoteMediator,
    pager: Pager<Int, RepoEntity>
) : ViewModel() {
    fun setLogin(login: String) {
        remoteMediator.login = login
    }

    val repoPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toRepositoryItem() }
        }
        .cachedIn(viewModelScope)
}