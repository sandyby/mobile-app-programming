package com.example.lab_week_02_b_sandy

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
        private const val TAG = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bg_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.return_btn).setOnClickListener {
            finish()
        }

        if (intent != null) {
            val colorCode = intent.getStringExtra(COLOR_KEY)
            Log.d(TAG, "kocak: $colorCode.toString()")
            val bgScreen = findViewById<ConstraintLayout>(R.id.bg_screen)

            try {
                bgScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            } catch (ex: IllegalArgumentException) {
                Intent().let { errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                }
            }
            val resMsg = findViewById<TextView>(R.id.color_code_result_msg)
            resMsg.text = getString(R.string.color_code_result_msg, colorCode?.uppercase())
        }
    }
}