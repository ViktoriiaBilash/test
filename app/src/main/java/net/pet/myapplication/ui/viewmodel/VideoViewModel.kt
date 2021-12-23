package net.pet.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import net.pet.myapplication.data.network.VideoPagingDataSource
import net.pet.myapplication.model.VideoItemUI

class VideoViewModel : ViewModel() {

    private val pagingDataSource = VideoPagingDataSource(query = "dog")

    val newData : Flow<PagingData<VideoItemUI>> = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
    pagingSourceFactory = {pagingDataSource}
    ).flow.cachedIn(viewModelScope)
}