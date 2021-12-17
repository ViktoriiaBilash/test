package net.pet.myapplication.retrofit.repositoryImages

import net.pet.myapplication.model.Image
import net.pet.myapplication.retrofit.`interface`.RetrofitServices

class ApiRepositoryImpl(private val apiService: RetrofitServices) : ApiRepository {

    override suspend fun getAll(): List<Image> {
        val response = apiService.getList(queryString = "cats")

        when (response.isSuccessful) {
            true -> {
                return response.body()?.images ?: listOf()
            }
            false -> {
                throw Exception("Network error with code: ${response.code()}")
            }
        }
    }
}