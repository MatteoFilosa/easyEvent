package com.example.easyEvent.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easyEvent.R

class GetEventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_events)

        // val city = intent.getStringExtra("city").toString()

        GetEventsFragment.newInstance()
    }
}