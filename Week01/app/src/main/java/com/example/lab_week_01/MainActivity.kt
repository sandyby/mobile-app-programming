package com.example.lab_week_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val finalDisplay = findViewById<TextView>(R.id.tvFinal)
        val submitBtn = findViewById<Button>(R.id.btnSubmit)

        submitBtn.setOnClickListener {
            val nameInput = findViewById<TextInputEditText>(R.id.tietName)?.text.toString().trim()
            val nimInput = findViewById<TextInputEditText>(R.id.tietNIM)?.text.toString().trim()
            if (nameInput.isEmpty() || nimInput.isEmpty()) {
                Toast.makeText(this, getString(R.string.field_empty), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            } else if (nimInput.length > 11) {
                Toast.makeText(this, getString(R.string.nim_too_long), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            } else {
                finalDisplay?.text = "Hello! My name is ${nameInput} and my NIM is ${nimInput}!"
            }
        }
    }
}