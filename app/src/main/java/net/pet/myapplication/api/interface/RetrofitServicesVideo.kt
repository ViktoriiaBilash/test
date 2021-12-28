package net.pet.myapplication.api.`interface`

import androidx.annotation.IntRange
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
                        @Query("q") queryString: String,
                        @Query("page") @IntRange(from = 1) page : Int = 1,
                        @Query("per_page") @IntRange(from = 1, to = 20) pageSize: Int = 20

    //return model VideoResponse.kt. It is generated as "Kotlin data class from Json"
    ): Response<VideosResponse>
}