package com.example.lab_week_06

import android.graphics.Color
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.loader.ImageLoader
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender
import com.google.android.material.card.MaterialCardView

private val FEMALE_SYMBOL = "\u2640"
private val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: CatAdapter.OnClickListener
) :
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

    private val catCardView: MaterialCardView by lazy {
        containerView.findViewById(R.id.mcvCatItem)
    }

    fun bindData(cat: CatModel) {
        containerView.setOnClickListener {
            onClickListener.onItemClick(cat)
        }

        imageLoader.loadImage(cat.imageUrl, catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
            CatBreed.Abyssinian -> "Abyssinian"
            CatBreed.Bengal -> "Bengal"
            CatBreed.NorwegianForest -> "Norwegian Forest"
            CatBreed.Birman -> "Birman"
            CatBreed.MaineCoon -> "Maine Coon"
            CatBreed.Persian -> "Persian"
            CatBreed.RussianBlue -> "Russian Blue"
            CatBreed.Siamese -> "Siamese"
            CatBreed.ScottishFold -> "Scottish Fold"
            CatBreed.Sphynx -> "Sphynx"
            CatBreed.Ragdoll -> "Ragdoll"
            else -> "Unknown"
        }
        catBiographyView.text = cat.biography
        when (cat.gender) {
            Gender.Female -> {
                catGenderView.text = FEMALE_SYMBOL
                catCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        containerView.context,
                        R.color.gender_pink
                    )
                )
            }

            Gender.Male -> {
                catGenderView.text = MALE_SYMBOL
                catCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        containerView.context,
                        R.color.gender_blue
                    )
                )
            }

            else -> {
                catGenderView.text = UNKNOWN_SYMBOL
                catCardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        containerView.context,
                        R.color.gender_unknown
                    )
                )
            }
        }
    }
}