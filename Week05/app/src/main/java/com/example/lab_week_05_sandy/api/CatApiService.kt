package com.example.lab_week_05_sandy.api

import com.example.lab_week_05_sandy.model.ImageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    fun searchImages(
        @Query("limit") limit: Int,
        @Query("size") format: String,

        /*
        * String data type
        * */
//    ): Call<String>

        /*
        * Lisst<ImageData> data type
        * */
    ): Call<List<ImageData>>
}