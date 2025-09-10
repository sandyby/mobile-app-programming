package com.example.lab_week_02_c_sandy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val DEBUG = "DEBUG"
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
        Log.d(DEBUG, "onCreate main cuy")

        val btnClickListener = View.OnClickListener {
            view ->
            when (view.id) {
                R.id.btn_standard -> startActivity(Intent(this, StandardActivity::class.java))
                R.id.btn_single_top -> startActivity(Intent(this, SingleTopActivity::class.java))
            }
        }
        findViewById<Button>(R.id.btn_standard).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_single_top).setOnClickListener(btnClickListener)

    }
}