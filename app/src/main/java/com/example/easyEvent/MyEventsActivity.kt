package com.example.easyEvent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyEventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_events)

        val eventText = findViewById<TextView>(R.id.eventResult)

        val db = Firebase.firestore
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Success:","${document.id} => ${document.data}")
                    eventText.text = document.data.toString()

                }
            }
            .addOnFailureListener { exception ->
                Log.w( "Error", "Error getting documents.", exception)
            }
    }
}