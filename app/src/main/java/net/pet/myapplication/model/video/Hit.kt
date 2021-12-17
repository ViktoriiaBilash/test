package net.pet.myapplication.model.video

import com.google.gson.annotations.SerializedName

//Kotlin data class from Json
data class Hit(
    val comments: Int,
    val downloads: Int,
    val duration: Int,
    val id: Int,
    val likes: Int,
    val pageURL: String,
    val picture_id: String,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,

    //changed name - "videos" in json file, "video"- Model VideoItemUI.kt
    @SerializedName("videos")
    val video: Video,
    val views: Int
)