package com.example.lab_week_05_sandy.model

import com.squareup.moshi.Json

data class  ImageData(
    @field:Json(name = "url") val imageUrl: String,
    val breeds: List<CatBreedData>
)