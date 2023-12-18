package com.example.osrsdex.activities

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.osrsdex.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnPlayerLoadoutsViewLoadout)
        btn.setOnClickListener(){onClickViewLoadouts()}

    }

    fun onClickCreateLoadout(){}

    fun onClickViewLoadouts()
    {
        val intent = Intent(applicationContext, SelectLoadoutActivity::class.java)
        startActivity(intent)

    }
}