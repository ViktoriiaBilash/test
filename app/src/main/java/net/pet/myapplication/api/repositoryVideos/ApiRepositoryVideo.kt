package net.pet.myapplication.api.repositoryVideos

import net.pet.myapplication.model.video.VideosResponse

interface ApiRepositoryVideo {
    suspend fun getAll(query: String, pageNumber: Int, pageSize: Int): VideosResponse?
}