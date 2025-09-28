package com.example.lab_week_05_sandy

import GlideLoader
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_week_05_sandy.api.CatApiService
import com.example.lab_week_05_sandy.model.ImageData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {
    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val req = original.newBuilder().header("x-api-key", BuildConfig.CAT_API_KEY)
            .method(original.method(), original.body()).build()
        Log.d(MAIN_ACTIVITY, "API Key sent: ${BuildConfig.CAT_API_KEY}")
        chain.proceed(req)
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/").client(okHttpClient)
            .addConverterFactory(/*
                * not using Moshi
                * */
//                ScalarsConverterFactory.create()

                /*
                * using Moshi
                * */
                MoshiConverterFactory.create()
            ).build()
    }

    private val catApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }

    private val apiResponseView: TextView by lazy {
        findViewById(R.id.tvAPIResponse)
    }

    private val breedNameView: TextView by lazy {
        findViewById(R.id.tvBreedName)
    }
    private val breedTemperamentView: TextView by lazy {
        findViewById(R.id.tvBreedTemperament)
    }

    private val imageResultView: ImageView by lazy {
        findViewById(R.id.ivImageResult)
    }

    private val imageLoader: ImageLoader by lazy {
        GlideLoader(this)
    }

    private val loadingBarLoader: ImageView by lazy {
        findViewById(R.id.ivLoadingBar)
    }

    private fun showLoadingBar() {
        imageResultView.setImageResource(R.drawable.loading_bar)
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        imageResultView.startAnimation(rotate)
//        loadingBarLoader.visibility = View.VISIBLE
//        imageResultView.visibility = View.GONE
//        loadingBarLoader.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_anim))
    }

    private fun hideLoadingBar(imageUrl: String) {
        imageResultView.clearAnimation()
        imageLoader.loadImage(imageUrl, imageResultView)

        //        loadingBarLoader.clearAnimation()
//        loadingBarLoader.visibility = View.GONE
//        imageResultView.visibility = View.VISIBLE
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

        /*
        * next cat button to fetch a new one
        * */

        val btnNextCat: Button = findViewById<Button>(R.id.btnNextCat)
        btnNextCat.setOnClickListener {
            getCatImageResponse()
        }
    }

    private fun getCatImageResponse() {
        showLoadingBar()

        val call = catApiService.searchImages(
            1, "full"
        )

        /*
        * String data type
        * */

//        call.enqueue(object : Callback<String> {
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.e(MAIN_ACTIVITY, "Failed to get the responses", t)
//            }

        /*
        * List<ImageData> data type
        * */

        call.enqueue(object : Callback<List<ImageData>> {
            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
                imageResultView.clearAnimation()
                imageResultView.setImageResource(R.drawable.placeholder_img)
                Log.e(MAIN_ACTIVITY, "Failed to get the responses", t)
            }


            override fun onResponse(
                /*
                * String data type
                * */
//                call: Call<String>, response: Response<String>

                /*
                * List<ImageData> data type
                * */
                call: Call<List<ImageData>>, response: Response<List<ImageData>>
            ) {
                if (response.isSuccessful) {
                    /*
                    * raw JSON fetched by Retrofit
                    * */
                    //                    apiResponseView.text = response.body()

                    /*
                    * cleaned parsed JSON to object by Moshi
                    * */

                    //                    Log.d(MAIN_ACTIVITY, response.body().toString())
//                    val image = response.body()
//                    val firstImage = image?.firstOrNull()?.imageUrl ?: "No URL"
//                    apiResponseView.text = getString(
//                        R.string.image_placeholder,
//                        firstImage
//                    )

                    /*
                    * loaded image by Glide using GlideLoader
                    * */

                    Log.d(MAIN_ACTIVITY, response.body().toString())
                    val image = response.body()
                    val firstImage = image?.firstOrNull()

                    if (firstImage == null) return;

                    val imageUrl = firstImage.imageUrl.orEmpty()
                    if (imageUrl.isNotBlank()) {
//                        hideLoadingBar(imageUrl)
                        imageLoader.loadImage(imageUrl, imageResultView)
                    } else {
                        imageResultView.clearAnimation()
                        imageResultView.setImageResource(R.drawable.placeholder_img)
                        Log.d(MAIN_ACTIVITY, "Missing Image URL")
                    }

                    val catBreed = firstImage.breeds.orEmpty()
                    if (catBreed.isEmpty()) {
                        breedNameView.text = getString(R.string.breed_placeholder)
                        breedTemperamentView.text = getString(R.string.breed_placeholder)
                        return;
                    }

                    val firstCatBreed = catBreed.first()

                    breedNameView.text = firstCatBreed.name
                    breedTemperamentView.text = firstCatBreed.temperament
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