package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDisplayed = findViewById<TextView>(R.id.user)
        val logout_btn = findViewById<TextView>(R.id.logout_button)

        val userId = intent.getStringExtra("user_id")
        val email = intent.getStringExtra("email_id")
        userDisplayed.text = "email :: $email"

        logout_btn.setOnClickListener{

            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }


}