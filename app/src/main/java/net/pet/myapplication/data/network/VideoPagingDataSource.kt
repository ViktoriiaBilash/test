package net.pet.myapplication.data.network

import androidx.paging.PagingSource
import kotlinx.coroutines.delay
import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.usecases.GetVideoResponseUseCase
import javax.inject.Inject

class VideoPagingDataSource(private val query: String) : PagingSource<Int, VideoItemUI>() {

    @Inject
    lateinit var responseUseCase: GetVideoResponseUseCase

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoItemUI> {
        delay(600)
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(20)
        var data: List<VideoItemUI>
        try {
            data = responseUseCase(query, pageNumber, pageSize)
        } catch (exception: Exception) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
        val nextKey = if (data.size < pageSize) null else pageNumber + 1
        val prevKey = if (pageNumber == 1) null else pageNumber - 1
        return LoadResult.Page(data, prevKey, nextKey)
    }
}