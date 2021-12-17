package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.R

class ProfileInfosActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_infos)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        val emailText = findViewById<TextView>(R.id.email)
        val nameText = findViewById<TextView>(R.id.name)
        val surnameText = findViewById<TextView>(R.id.surname)
        val ageText = findViewById<TextView>(R.id.age)
        val signout_btn = findViewById<TextView>(R.id.signout_button)
        loadProfile()
    }

    private fun loadProfile() {

        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        emailText.text = "Email  -- > "+user?.email

        userreference?.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                nameText.text = "Firstname - - > "+snapshot.child("name").value.toString()
                surnameText.text = "Last name - -> "+snapshot.child("surname").value.toString()
                ageText.text = "Last name - -> "+snapshot.child("age").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        signout_btn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@ProfileInfosActivity, LoginActivity::class.java))
            finish()
        }
    }
}