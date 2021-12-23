package net.pet.myapplication.api.repositoryVideos

import net.pet.myapplication.model.video.VideosResponse
import net.pet.myapplication.api.`interface`.RetrofitServicesVideo

class ApiRepositoryVideoImpl(private val apiService: RetrofitServicesVideo) : ApiRepositoryVideo {
    override suspend fun getAll(query: String, pageNumber: Int, pageSize: Int): VideosResponse? {
        val response = apiService.getList(queryString = "cats")
        when (response.isSuccessful) {
            true -> {
                return response.body()
            }
            false -> {
                throw Exception("Network error with code: ${response.code()}")
            }
        }
    }
}