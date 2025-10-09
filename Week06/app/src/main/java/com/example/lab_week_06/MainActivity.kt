package com.example.lab_week_06

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.loader.GlideImageLoader
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
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

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ), CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ), CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.Abyssinian,
                    "Zephyr",
                    "Energetic and loves to climb high places",
                    "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"  // placeholder
                ), CatModel(
                    Gender.Female,
                    CatBreed.Bengal,
                    "Luna",
                    "Wild markings, playful and bold",
                    "https://cdn2.thecatapi.com/images/O3btzLlsO.png"
                ),

                CatModel(
                    Gender.Male,
                    CatBreed.Persian,
                    "Fluff",
                    "Regal and calm, loves lounging",
                    "https://cdn2.thecatapi.com/images/6qi.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.Siamese,
                    "Sora",
                    "Vocal, curious, and people-oriented",
                    "https://cdn2.thecatapi.com/images/ai6.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.Sphynx,
                    "Baldur",
                    "Hairless but full of personality",
                    "https://cdn2.thecatapi.com/images/abc.jpg"
                ), CatModel(
                    Gender.Female,
                    CatBreed.NorwegianForest,
                    "Freya",
                    "Thick coat, loves cold weather",
                    "https://cdn2.thecatapi.com/images/def.jpg"
                ), CatModel(
                    Gender.Female,
                    CatBreed.Abyssinian,
                    "Zara",
                    "Playful, loves to hop and explore",
                    "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.Bengal,
                    "Toro",
                    "Marked like wild cats, but domestic heart",
                    "https://cdn2.thecatapi.com/images/O3btzLlsO.png"
                ), CatModel(
                    Gender.Female,
                    CatBreed.Birman,
                    "Cleo",
                    "Soft and gentle, loves cuddling",
                    "https://cdn2.thecatapi.com/images/1p-X9wB3s.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.MaineCoon,
                    "Goliath",
                    "Large, friendly, loves company",
                    "https://cdn2.thecatapi.com/images/13Z4.jpg"
                ), CatModel(
                    Gender.Female,
                    CatBreed.Persian,
                    "Lola",
                    "Regal, calm, content with lounging",
                    "https://cdn2.thecatapi.com/images/6qi.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.RussianBlue,
                    "Artemis",
                    "Quiet, shy but deeply loyal",
                    "https://cdn2.thecatapi.com/images/rsd.jpg"
                ), CatModel(
                    Gender.Female,
                    CatBreed.Siamese,
                    "Mia",
                    "Talkative, social, loves attention",
                    "https://cdn2.thecatapi.com/images/ai6.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.ScottishFold,
                    "Ollie",
                    "Charming fold-ears and soft personality",
                    "https://cdn2.thecatapi.com/images/3bk.jpg"
                ), CatModel(
                    Gender.Female,
                    CatBreed.Sphynx,
                    "Nyx",
                    "Hairless and bold, full of character",
                    "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"
                ), CatModel(
                    Gender.Male,
                    CatBreed.Ragdoll,
                    "Sam",
                    "Flops into your arms, loves being held",
                    "https://cdn2.thecatapi.com/images/vjb.jpg"
                )
            )
        )
    }

    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this).setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}").setPositiveButton("OK") { _, _ -> }
            .show()
    }
}