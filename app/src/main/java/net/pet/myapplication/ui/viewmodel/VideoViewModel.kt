package net.pet.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import net.pet.myapplication.data.network.VideoPagingDataSource
import net.pet.myapplication.utils.Constants

class VideoViewModel : ViewModel() {

    private val queryFlow = MutableStateFlow(Constants.DEFAULT_QUERY)

    val newData = queryFlow.flatMapLatest {query ->
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { VideoPagingDataSource(query) }
        ).flow.cachedIn(viewModelScope)
    }

    fun updateQuery(_query: String) {
       queryFlow.value = _query
    }
}