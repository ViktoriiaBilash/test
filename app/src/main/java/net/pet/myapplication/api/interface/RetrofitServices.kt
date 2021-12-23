package net.pet.myapplication.api.`interface`

import net.pet.myapplication.model.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("api/")
    suspend fun getList(@Query("key") apiKey: String = "24842209-4126763b808500920acf6097b",
                @Query("q") queryString: String
    ): Response<ImagesResponse>
}