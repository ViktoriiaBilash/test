package net.pet.myapplication.di

import androidx.room.Room
import net.pet.myapplication.retrofit.`interface`.RetrofitServices
import net.pet.myapplication.retrofit.`interface`.RetrofitServicesVideo
import net.pet.myapplication.retrofit.repositoryImages.ApiRepository
import net.pet.myapplication.retrofit.repositoryImages.ApiRepositoryImpl
import net.pet.myapplication.retrofit.repositoryVideos.ApiRepositoryVideo
import net.pet.myapplication.retrofit.repositoryVideos.ApiRepositoryVideoImpl
import net.pet.myapplication.room.Database
import net.pet.myapplication.room.EmployeeDao
import net.pet.myapplication.room.repository.RoomRepository
import net.pet.myapplication.room.repository.RoomRepositoryImpl
import net.pet.myapplication.usecases.GetVideoResponseUseCase
import net.pet.myapplication.utils.Constants
import net.pet.myapplication.viewmodel.ImageViewModel
import net.pet.myapplication.viewmodel.VideoViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    viewModel { ImageViewModel(get()) }
    viewModel { VideoViewModel(get()) }

    single<RoomRepository> { RoomRepositoryImpl(get()) }
    single {
        Room.databaseBuilder(androidContext(), Database::class.java, "database")
            .fallbackToDestructiveMigration().build()
    }
    single { provideDao(get()) }
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single { provideApiService(get())}
    single { provideRetrofitClient() }
    single <ApiRepositoryVideo> {ApiRepositoryVideoImpl(get()) }
    single { provideApiServiceVideo(get()) }

    single { GetVideoResponseUseCase(get())}

}

private fun provideDao(database: Database): EmployeeDao {
    return database.employeeDao()
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

private fun provideApiServiceVideo(retrofit: Retrofit) : RetrofitServicesVideo{
    return retrofit.create((RetrofitServicesVideo::class.java))
}

private fun provideApiService(retrofit: Retrofit): RetrofitServices {
    return retrofit.create(RetrofitServices::class.java)
}
