package com.example.easyEvent.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easyEvent.R

class EventListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_list)

        EventListFragment.newInstance()
    }
}