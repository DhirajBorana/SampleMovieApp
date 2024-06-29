package com.example.movies.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.data.model.Movie
import com.example.movies.ui.theme.MoviesTheme

@Composable
fun MovieCarousel(
    modifier: Modifier = Modifier,
    section: String,
    movies: List<Movie>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background
            ),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = section,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            movies.forEach { movie ->
                item(key = movie.id) {
                    MovieItem(
                        movie = movie
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MovieCarouselPreview() {
    MoviesTheme {
        MovieCarousel(
            section = "Now Playing",
            movies = listOf(
                Movie(title = "Movie 1"),
                Movie(title = "Movie 2"),
                Movie(title = "Movie 3"),
                Movie(title = "Movie 4"),
            )
        )
    }
}