package com.example.movies.ui.vm

import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.Movie
import com.example.movies.data.model.MovieResponse
import com.example.movies.data.repo.MovieRepo
import com.example.movies.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MovieRepo
) : ViewModel() {

    init {
        getNowPlaying()
        getTopRated()
    }

    private val _nowPlayingMovieResponse = MutableStateFlow<Resource<MovieResponse>>(Resource.Loading())
    val nowPlayingMovieResponse: StateFlow<Resource<MovieResponse>>
        get() = _nowPlayingMovieResponse

    private val _topRatedMovieResponse = MutableStateFlow<Resource<MovieResponse>>(Resource.Loading())
    val topRatedResponse: StateFlow<Resource<MovieResponse>>
        get() = _topRatedMovieResponse

    private val _trendingByDay = MutableStateFlow<Resource<MovieResponse>>(Resource.Loading())
    private val _trendingByWeek = MutableStateFlow<Resource<MovieResponse>>(Resource.Loading())



    val trendingMoviesCombinedData = combine(
        _trendingByDay, _trendingByDay
    ) { day, week ->
        TrendingMovies(byDay = day.data?.movies, byWeek = week.data?.movies)
    }


    private fun getNowPlaying(page: Int = 1) = viewModelScope.launch(Dispatchers.Main) {
        val result: Resource<MovieResponse> = repo.getNowPlaying(page = page)
        _nowPlayingMovieResponse.value = result
    }

    private fun getTopRated(page: Int = 1) = viewModelScope.launch(Dispatchers.Main) {
        val result: Resource<MovieResponse> = repo.getTopRated(page = page)
        _topRatedMovieResponse.value = result
    }

    private fun getTrendingByDay(page: Int = 1) = viewModelScope.launch(Dispatchers.Main) {
        val result: Resource<MovieResponse> = repo.getTrendingByDay(page = page)
        _trendingByDay.value = result
    }

    private fun getTrendingByWeek(page: Int = 1) = viewModelScope.launch(Dispatchers.Main) {
        val result: Resource<MovieResponse> = repo.getTrendingByWeek(page = page)
        _trendingByWeek.value = result
    }

    data class TrendingMovies(
        val byDay: List<Movie>? = emptyList(),
        val byWeek: List<Movie>? = emptyList()
    )
}