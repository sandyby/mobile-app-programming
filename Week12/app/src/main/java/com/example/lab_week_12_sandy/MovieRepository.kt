package com.example.lab_week_12_sandy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lab_week_12_sandy.api.MovieService
import com.example.lab_week_12_sandy.model.Movie

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = BuildConfig.THEMOVIEDB_API_KEY

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = movieLiveData

    private val errorMessageLiveData = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = errorMessageLiveData

    suspend fun fetchMovies(){
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (e: Exception){
            errorMessageLiveData.value = "An error occurred: ${e.message}"
        }
    }
}