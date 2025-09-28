package com.example.lab_week_05_sandy

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_week_05_sandy.api.CatApiService
import com.example.lab_week_05_sandy.model.ImageData
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val req = original.newBuilder()
                .header("x-api-key", BuildConfig.CAT_API_KEY)
                .method(original.method(), original.body())
                .build()
            Log.d(MAIN_ACTIVITY, "API Key sent: ${BuildConfig.CAT_API_KEY}")
            chain.proceed(req)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create()
            ).build()
    }

    private val catApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }

    private val apiResponseView: TextView by lazy {
        findViewById(R.id.tvAPIResponse)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getCatImageResponse()
    }

    private fun getCatImageResponse() {
        val call = catApiService.searchImages(
            1,
            "full"
        )
        call.enqueue(object : Callback<List<ImageData>> {
            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
                Log.e(MAIN_ACTIVITY, "Failed to get the responses", t)
            }

            override fun onResponse(
                call: Call<List<ImageData>>,
                response: Response<List<ImageData>>
            ) {
//                if (response.isSuccessful) {
//                    apiResponseView.text = response.body()
//                } else {
//                    Log.e(
//                        MAIN_ACTIVITY,
//                        "Failed to get responses\n" + response.errorBody()?.string().orEmpty()
//                    )
//                }
                if (response.isSuccessful) {
                    val image = response.body()
                    val firstImage = image?.firstOrNull()?.imageUrl ?: "No URL"
                    apiResponseView.text = getString(R.string.image_placeholder, firstImage)
                } else {
                    Log.e(
                        MAIN_ACTIVITY,
                        "Error: ${response.code()} - ${response.errorBody()?.string()}"
                    )
                }
            }
        })
    }

    companion object {
        const val MAIN_ACTIVITY = "MAIN_ACTIVITY"
    }
}