package com.example.lab_week_06.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lab_week_06.R

class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context).load(imageUrl).placeholder(R.drawable.placeholder_70).error(R.drawable.placeholder_70)
            .centerCrop().into(imageView)
    }
}