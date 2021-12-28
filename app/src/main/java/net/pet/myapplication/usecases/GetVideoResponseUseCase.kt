package net.pet.myapplication.usecases

import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.api.repositoryVideos.ApiRepositoryVideo

class GetVideoResponseUseCase(private val repository: ApiRepositoryVideo) {

    suspend operator fun invoke(query: String, pageNumber: Int, pageSize: Int): List<VideoItemUI> {

        val response = repository.getAll(query, pageNumber, pageSize)
        return response.let { videoResponse ->
            val hits = videoResponse?.hits ?: listOf()

            hits.map {
                val video = it.video.medium
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