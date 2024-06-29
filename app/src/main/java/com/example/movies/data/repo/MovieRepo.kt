package com.example.movies.data.repo

import com.example.movies.data.datastore.MovieService
import com.example.movies.data.model.MovieResponse
import com.example.movies.data.utils.Resource
import com.example.movies.data.utils.safeApiCall
import javax.inject.Inject

private const val TRENDING_BY_DAY = "day"
private const val TRENDING_BY_WEEK = "week"

class MovieRepo @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getNowPlaying(page: Int): Resource<MovieResponse> = safeApiCall {
        movieService.getNowPlaying(page = page)
    }

    suspend fun getPopular(page: Int) = safeApiCall {
        movieService.getPopular(page = page)
    }

    suspend fun getTopRated(page: Int) = safeApiCall {
        movieService.getTopRated(page = page)
    }

    suspend fun getTrendingByDay(page: Int) = safeApiCall {
        movieService.getTrending(time = TRENDING_BY_DAY, page = page)
    }

    suspend fun getTrendingByWeek(page: Int) = safeApiCall {
        movieService.getTrending(time = TRENDING_BY_WEEK, page = page)
    }
}