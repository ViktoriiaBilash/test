package net.pet.myapplication.data.network

import androidx.paging.PagingSource
import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.usecases.GetVideoResponseUseCase

class VideoPagingDataSource (private val responseUseCase: GetVideoResponseUseCase, private val query: String) : PagingSource<Int, VideoItemUI>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoItemUI> {
        if (query.isEmpty()){
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(20)

        val data = responseUseCase.invoke(query, pageNumber, pageSize)

        val nextKey = if(data.size < pageSize) null else pageNumber+1
        val prevKey = if (pageNumber == 1) null else pageNumber-1
        return LoadResult.Page(data, prevKey, nextKey)
    }
}