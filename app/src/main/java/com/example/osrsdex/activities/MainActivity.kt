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

        val btnViewLoadouts: Button = findViewById(R.id.btnPlayerLoadoutsViewLoadout)
        val btnCreateLoadout: Button = findViewById(R.id.btnPlayerLoadoutsCreate)
        btnViewLoadouts.setOnClickListener(){onClickViewLoadouts()}
        btnCreateLoadout.setOnClickListener(){onClickCreateLoadout()}

    }

    fun onClickCreateLoadout(){
        val intent = Intent(applicationContext, EditLoadoutActivity::class.java)
        startActivity(intent)
    }

    fun onClickViewLoadouts()
    {
        val intent = Intent(applicationContext, SelectLoadoutActivity::class.java)
        startActivity(intent)

    }
}