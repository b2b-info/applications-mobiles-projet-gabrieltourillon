package com.example.osrsdex.activities.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.osrsdex.R
import com.example.osrsdex.activities.editloadoutactivity.EditLoadoutActivity
import com.example.osrsdex.activities.selectloadout.SelectLoadoutActivity
import com.example.osrsdex.network.HiScoreAPI
import com.example.osrsdex.network.HiScoreAPIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnViewLoadouts: Button = findViewById(R.id.btnPlayerLoadoutsViewLoadout)
        val btnCreateLoadout: Button = findViewById(R.id.btnPlayerLoadoutsCreate)
        val btnTester: Button = findViewById(R.id.btnTester)
        btnViewLoadouts.setOnClickListener(){onClickViewLoadouts()}
        btnCreateLoadout.setOnClickListener(){onClickCreateLoadout()}
        btnTester.setOnClickListener(){onClickTestButton()}

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

    fun onClickTestButton()
    {
        val client = HiScoreAPIClient.getInstance().create(HiScoreAPI::class.java)
        lifecycleScope.launch {
            try
            {
                val response = withContext(Dispatchers.IO){client.getMyStatsGameMode()}
                if(response.isSuccessful)
                {
                    val body = response.body()
                    if(body != null)
                    {
                        Log.d("OSRSDEX", "onClickTestButton: Successful request:" + body.toString())
                    }
                }
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
    }

}