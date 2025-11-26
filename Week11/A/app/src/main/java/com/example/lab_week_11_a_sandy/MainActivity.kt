package com.example.lab_week_11_a_sandy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_11_a_sandy.datastores.SettingsApplication
import com.example.lab_week_11_a_sandy.datastores.SettingsViewModel
import com.example.lab_week_11_a_sandy.preferences.PreferenceViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val preferenceWrapper = (application as SettingsApplication).settingsStore
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(preferenceWrapper) as T
            }
        })[SettingsViewModel::class.java]

        preferenceViewModel.textLiveData.observe(this) {
            findViewById<TextView>(
                R.id.tv_mainActivity
            ).text = it
        }
        findViewById<Button>(R.id.btn_mainActivity).setOnClickListener {
            preferenceViewModel.saveText(
                findViewById<EditText>(R.id.et_mainActivity).text.toString()
            )
        }
    }
}