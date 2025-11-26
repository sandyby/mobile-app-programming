package com.example.lab_week_10_sandy.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Total::class], version = 1)
abstract class TotalDatabase: RoomDatabase() {
    abstract fun totalDao(): TotalDao
}