package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val splashScreenTimeout: Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splashScreenTimeout)


    }

}
