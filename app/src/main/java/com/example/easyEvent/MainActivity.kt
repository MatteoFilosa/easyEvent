package com.example.easyEvent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import com.example.easyEvent.ui.EventListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    lateinit var imageUri : Uri
    lateinit var propic: CircleImageView

    var database = FirebaseDatabase.getInstance("https://easyevent-5730d-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
    val uid = FirebaseAuth.getInstance().currentUser!!.uid
    val uidRef = database.child("users").child(uid)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDisplayed = findViewById<TextView>(R.id.user)
        val editprofile_btn = findViewById<TextView>(R.id.editprofile_button)
        val viewevents_btn = findViewById<TextView>(R.id.view_events)
        val searchEvent = findViewById<SearchView>(R.id.searchE)

        //New part


        var storageRef = FirebaseStorage.getInstance("gs://easyevent-5730d.appspot.com").reference.child("images/$uid")

        propic = findViewById<CircleImageView>(R.id.profile_image)

        imageUri = Uri.parse("android.resource://com.example.easyEvent/drawable/default_propic")



        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                userDisplayed.text = snapshot!!.child("name").getValue(String::class.java) + "!"
            } else {
                Log.d("TAG", task.exception!!.message!!)
            }
            //Downalod original propic
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri).into(propic)
                imageUri = uri
            }

        }

        editprofile_btn.setOnClickListener{

            startActivity(Intent(this, ProfileInfosActivity::class.java))
        }

        viewevents_btn.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        searchEvent.setOnQueryTextListener(object : SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(query: String?): Boolean{
                val city = searchEvent.query
                searchEvent.clearFocus()
                val intent =
                    Intent(this@MainActivity, GetEventsActivity::class.java)
                intent.putExtra("city", city)
                startActivity(intent)

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })


    }


}