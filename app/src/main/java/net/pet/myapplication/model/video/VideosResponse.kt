package net.pet.myapplication.model.video

data class VideosResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)