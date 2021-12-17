package net.pet.myapplication.retrofit.`interface`

import net.pet.myapplication.model.video.VideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServicesVideo {
    //base api
    @GET("api/videos/")
    //key get from API docs
    suspend fun getList(@Query("key") apiKey: String = "24842209-4126763b808500920acf6097b",
                        //q is got from API docs. Its a parameter of query.
                        @Query("q") queryString: String
    //return model VideoResponse.kt. It is generated as "Kotlin data class from Json"
    ): Response<VideosResponse>
}