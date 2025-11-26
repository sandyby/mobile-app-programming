package com.example.lab_week_11_a_sandy.datastores

import android.app.Application

class SettingsApplication: Application() {
    lateinit var settingsStore: SettingsStore
    override fun onCreate() {
        super.onCreate()
        settingsStore = SettingsStore(this)
    }
}