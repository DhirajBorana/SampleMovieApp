package com.example.movies.ui.utils


object MoviePosterUtils {
    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p"

    fun generatePosterUrl(url: String, size: PosterSize = PosterSize.Width500): String {
        return "$POSTER_BASE_URL/${size.size}/$url"
    }

    enum class PosterSize(val size: String) {
        Width500(size = "w500")
    }
}