package com.example.lab_week_10_sandy.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Total::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [

    ]
)
abstract class TotalDatabase : RoomDatabase() {
    class AutoMigration : AutoMigrationSpec

    abstract fun totalDao(): TotalDao
}