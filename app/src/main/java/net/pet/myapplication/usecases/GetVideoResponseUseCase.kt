package net.pet.myapplication.usecases

import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.retrofit.repositoryVideos.ApiRepositoryVideo

class GetVideoResponseUseCase(private val repository: ApiRepositoryVideo) {

    suspend operator fun invoke(): List<VideoItemUI> {
        val response = repository.getAll()
//response contains list of hits
        return response.let { videoResponse ->
            val hits = videoResponse?.hits ?: listOf()

//hits = Hit.kt
            hits.map {
                //The mapping transformation creates a collection from the results
                // of a function on the elements of another collection.
                //every list of video (in list of hit) contains several options
                //video: medium, tiny, large etc
                val video = it.video.medium
                //create VideoItemUI from video and it= Hit.kt
                VideoItemUI(
                    height = video.height,
                    size = video.size,
                    url = video.url,
                    width = video.width,
                    pictureId = it.picture_id,
                    views = it.views,
                    tags = it.tags
                )
            }
        }
    }
}