package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.easyEvent.overview.OverviewActivity
import com.example.easyEvent.overview.OverviewFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDisplayed = findViewById<TextView>(R.id.user)
        val editprofile_btn = findViewById<TextView>(R.id.editprofile_button)
        val viewevents_btn = findViewById<TextView>(R.id.view_events)

        val userId = intent.getStringExtra("user_id")
        val email = intent.getStringExtra("email_id")
        userDisplayed.text = "email :: $email"

        editprofile_btn.setOnClickListener{

            val intent = Intent(this@MainActivity, ProfileInfosActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        viewevents_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, OverviewActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }


    }


}