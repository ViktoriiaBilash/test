package net.pet.myapplication.api.repositoryVideos

import android.util.Log
import net.pet.myapplication.model.video.VideosResponse
import net.pet.myapplication.api.`interface`.RetrofitServicesVideo
import retrofit2.Response

class ApiRepositoryVideoImpl(private val apiService: RetrofitServicesVideo) : ApiRepositoryVideo {
    init {
        Log.e("TAG", "ApiRepositoryVideoImpl")
    }
    override suspend fun getAll(query: String, pageNumber: Int, pageSize: Int): VideosResponse? {
        Log.e("TAG", "ApiRepositoryVideoImpl getAll")
        val response : Response<VideosResponse> = apiService.getList(queryString = "cats")
        Log.e("TAG", "ApiRepositoryVideoImpl getAll   $response")
        when (response.isSuccessful) {
            true -> {
                return response.body()
            }
            false -> {
                Log.e("TAG", "Network error with code: ${response.code()}")
                throw Exception("Network error with code: ${response.code()}")
            }
        }
    }
}