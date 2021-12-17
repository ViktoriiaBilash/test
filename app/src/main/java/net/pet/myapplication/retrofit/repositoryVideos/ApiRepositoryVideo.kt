package net.pet.myapplication.retrofit.repositoryVideos

import net.pet.myapplication.model.video.VideosResponse

interface ApiRepositoryVideo {
    suspend fun getAll(): VideosResponse?
}