package com.example.lab_week_09_sandy.providers

import com.example.lab_week_09_sandy.Student
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiProvider {
    val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    val studentListAdapter by lazy {
        val type = Types.newParameterizedType(List::class.java, Student::class.java)
        moshi.adapter<List<Student>>(type)
    }
}