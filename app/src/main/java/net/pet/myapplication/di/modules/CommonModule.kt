package net.pet.myapplication.di.modules

import dagger.Module
import dagger.Provides
import net.pet.myapplication.api.`interface`.RetrofitServices
import net.pet.myapplication.api.`interface`.RetrofitServicesVideo
import net.pet.myapplication.api.repositoryVideos.ApiRepositoryVideo
import net.pet.myapplication.api.repositoryVideos.ApiRepositoryVideoImpl
import net.pet.myapplication.data.network.VideoPagingDataSource
import net.pet.myapplication.ui.viewmodel.VideoViewModel
import net.pet.myapplication.usecases.GetVideoResponseUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CommonModule {

    @Provides
    fun provideViewModel() : VideoViewModel{
        return VideoViewModel()
    }

    @Provides
    fun provideVideoPagingDataSource() : VideoPagingDataSource {
        return VideoPagingDataSource("cat")
    }

    @Provides
    fun provideGetVideoResponseUseCase() : GetVideoResponseUseCase {
        return GetVideoResponseUseCase(provideApiRepositoryVideo())
    }

    @Provides
    fun provideApiRepositoryVideo(): ApiRepositoryVideo{
        return ApiRepositoryVideoImpl(provideRetrofitServicesVideo(provideRetrofitClient()))
    }

    @Provides
    fun provideRetrofitServicesVideo(retrofit: Retrofit) : RetrofitServicesVideo{
        return retrofit.create((RetrofitServicesVideo::class.java))    }

    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            //It shows logs from api query
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build())
            .build()
    }
}