package com.example.movies.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movies.data.model.Movie
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.ui.utils.MoviePosterUtils

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
    ) {
        val posterUrl = MoviePosterUtils.generatePosterUrl(movie.posterPath.orEmpty())

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(.6f),
            model = posterUrl,
            contentDescription = "${movie.title} poster"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movie.title.orEmpty(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun MovieItemPreview() {
    MoviesTheme {
        MovieItem(
            movie = Movie(
                title = "Movie name"
            )
        )
    }
}