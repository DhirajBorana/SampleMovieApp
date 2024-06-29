package com.example.movies.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies.ui.compose.MovieCarousel
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.ui.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {

                val nowPlayingResponse = mainViewModel.nowPlayingMovieResponse.collectAsState()
                val topRatedResponse = mainViewModel.topRatedResponse.collectAsState()
                val trendingMoviesCombine = mainViewModel.trendingMoviesCombinedData.collectAsState(
                    initial = MainViewModel.TrendingMovies()
                )

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    MovieCarousel(
                        section = "Now Playing",
                        movies = nowPlayingResponse.value.data?.movies ?: emptyList()
                    )
                    MovieCarousel(
                        section = "Top Rated",
                        movies = topRatedResponse.value.data?.movies ?: emptyList()
                    )
                }
            }
        }
    }
}