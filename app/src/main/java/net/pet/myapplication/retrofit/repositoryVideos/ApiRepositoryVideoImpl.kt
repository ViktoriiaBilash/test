package net.pet.myapplication.retrofit.repositoryVideos

import net.pet.myapplication.model.video.VideosResponse
import net.pet.myapplication.retrofit.`interface`.RetrofitServicesVideo

class ApiRepositoryVideoImpl(private val apiService: RetrofitServicesVideo) : ApiRepositoryVideo {
    override suspend fun getAll(): VideosResponse? {
        //query to API
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