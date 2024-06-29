package com.example.movies.data.di

import com.example.movies.data.datastore.MovieService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "0224d16d7286ec7d639350eb53edca1f"
    private const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMjI0ZDE2ZDcyODZlYzdkNjM5MzUwZWI1M2VkY2ExZiIsIm5iZiI6MTcxOTY0MTkyOS4zMjgyODQsInN1YiI6IjVmMmFhZWU5ZDhjYzRhMDAzOTIyMzJmOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fQB-03RDKBbIRkeZU76IRHa6V7Y8BuDm3PxGv-yeHPA"

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()

                request.addHeader("api_key", API_KEY)
                request.addHeader("Authorization", "Bearer $ACCESS_TOKEN")
                request.addHeader("Accept","application/json")
                it.proceed(request.build())
            }
        return client.build()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}