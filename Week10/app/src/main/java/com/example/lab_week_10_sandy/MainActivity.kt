package com.example.lab_week_10_sandy

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab_week_10_sandy.database.Total
import com.example.lab_week_10_sandy.database.TotalDatabase
import com.example.lab_week_10_sandy.database.TotalObject
import com.example.lab_week_10_sandy.viewmodels.TotalViewModel
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    companion object {
        const val ID: Long = 1
    }

    private val db by lazy {
        prepareDatabase()
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[TotalViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeValueFromDatabase()
        prepareViewModel()
    }

    override fun onStart() {
        super.onStart()
        val lastSavedTotal = db.totalDao().getTotal(ID)
        Toast.makeText(this, lastSavedTotal.first().total.date, Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPause() {
        super.onPause()
        val total = viewModel.total.value!!
        val date = viewModel.date.value!!
        db.totalDao().update(Total(ID, TotalObject(total, date)))
    }

    private fun prepareDatabase(): TotalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java, "total-database"
        ).allowMainThreadQueries().build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initializeValueFromDatabase() {
        val total = db.totalDao().getTotal(ID)
        if (total.isEmpty()) {
            db.totalDao().insert(Total(id = 1, total = TotalObject(0, "")))
        } else {
            viewModel.setTotal(total.first().total.value)
            viewModel.setDate(total.first().total.date)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun prepareViewModel() {
        viewModel.total.observe(this, {
            updateText(it)
        })

        findViewById<Button>(R.id.btn_increment).setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    private fun updateText(total: Int) {
        findViewById<TextView>(R.id.tv_total).text =
            getString(R.string.tv_total, total)
    }
}