package com.example.lab_week_12_sandy.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>
)
