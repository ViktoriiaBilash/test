package net.pet.myapplication.di

import net.pet.myapplication.data.network.VideoPagingDataSource
import net.pet.myapplication.api.`interface`.RetrofitServices
import net.pet.myapplication.api.`interface`.RetrofitServicesVideo
import net.pet.myapplication.api.repositoryImages.ApiRepository
import net.pet.myapplication.api.repositoryImages.ApiRepositoryImpl
import net.pet.myapplication.api.repositoryVideos.ApiRepositoryVideo
import net.pet.myapplication.api.repositoryVideos.ApiRepositoryVideoImpl
import net.pet.myapplication.usecases.GetVideoResponseUseCase
import net.pet.myapplication.utils.Constants
import net.pet.myapplication.ui.viewmodel.ImageViewModel
import net.pet.myapplication.ui.viewmodel.VideoViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    viewModel { ImageViewModel(get()) }
    viewModel { VideoViewModel(get()) }

    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single { provideApiService(get())}
    single { provideRetrofitClient() }
    single <ApiRepositoryVideo> { ApiRepositoryVideoImpl(get()) }
    single { provideApiServiceVideo(get()) }

    single { GetVideoResponseUseCase(get())}
    single { VideoPagingDataSource(get()) }
}

private fun provideRetrofitClient(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        //It shows logs from api query
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build())
        .build()
}

private fun provideApiServiceVideo(retrofit: Retrofit) : RetrofitServicesVideo {
    return retrofit.create((RetrofitServicesVideo::class.java))
}

private fun provideApiService(retrofit: Retrofit): RetrofitServices {
    return retrofit.create(RetrofitServices::class.java)
}
