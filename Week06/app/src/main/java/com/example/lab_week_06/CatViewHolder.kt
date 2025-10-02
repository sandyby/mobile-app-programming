package com.example.lab_week_06

import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.loader.ImageLoader
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

private val FEMALE_SYMBOL = "\u2640"
private val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(containerView: View, private val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(containerView) {
    private val catBiographyView: TextView by lazy {
        containerView.findViewById(R.id.tvCatBiography)
    }
    private val catBreedView: TextView by lazy {
        containerView.findViewById(R.id.tvCatBreed)
    }
    private val catGenderView: TextView by lazy {
        containerView.findViewById(R.id.tvCatGender)
    }
    private val catNameView: TextView by lazy {
        containerView.findViewById(R.id.tvCatName)
    }
    private val catPhotoView: ImageView by lazy {
        containerView.findViewById(R.id.ivCatPhoto)
    }

    fun bindData(cat: CatModel) {
        imageLoader.loadImage(cat.imageUrl, catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
            else -> "Unknown"
        }
        catBiographyView.text = cat.biography
        catGenderView.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }
}