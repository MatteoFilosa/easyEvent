package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class GetEventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        /* not working
        val location = findViewById<TextView>(R.id.detail_location).toString()
        val mapsBtn = findViewById<TextView>(R.id.fab_view_map)
        Log.d("ECCOLO" ,location)

        mapsBtn.setOnClickListener() {

            val intent =
                Intent(this@GetEventsActivity, MapsActivity::class.java)
            intent.putExtra("location", location)
            startActivity(intent)



        }*/
    }


    }
