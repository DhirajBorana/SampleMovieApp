package com.example.movies.data.datastore

import com.example.movies.data.model.MovieResponse
import com.example.movies.data.utils.Resource
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): MovieResponse

    @GET("rending/movie/{time_window}")
    suspend fun getTrending(
        @Path("time_window") time: String,
        @Query("page") page: Int
    ): MovieResponse
}