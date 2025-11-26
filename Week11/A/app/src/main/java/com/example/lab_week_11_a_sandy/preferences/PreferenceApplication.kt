package com.example.lab_week_11_a_sandy.preferences

import android.app.Application
import android.content.Context

class PreferenceApplication : Application() {
    lateinit var preferenceWrapper: PreferenceWrapper
    override fun onCreate() {
        super.onCreate()
        preferenceWrapper = PreferenceWrapper(
            getSharedPreferences(
                "prefs",
                Context.MODE_PRIVATE
            )
        )
    }
}