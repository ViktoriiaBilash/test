package net.pet.myapplication.data.network

import android.util.Log
import androidx.paging.PagingSource
import kotlinx.coroutines.delay
import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.usecases.GetVideoResponseUseCase
import org.koin.java.KoinJavaComponent.inject

class VideoPagingDataSource(private val query: String) : PagingSource<Int, VideoItemUI>() {

    private val responseUseCase: GetVideoResponseUseCase by inject(GetVideoResponseUseCase::class.java)

    init {
        Log.e("TAG", "VideoPagingDataSource")
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoItemUI> {
        delay(1200)
        Log.e("TAG", "VideoPagingDataSource  load")
        if (query.isEmpty()){
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        val pageNumber = params.key ?: 1
        Log.e("TAG", "VideoPagingDataSource  load, pageNumber = $pageNumber")
        val pageSize = params.loadSize.coerceAtMost(20)

        val data = responseUseCase(query, pageNumber, pageSize)

        val nextKey = if(data.size < pageSize) null else pageNumber+1
        val prevKey = if (pageNumber == 1) null else pageNumber-1
        return LoadResult.Page(data, prevKey, nextKey)
    }
}