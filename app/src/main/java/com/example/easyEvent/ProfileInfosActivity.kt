package com.example.easyEvent

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File


class ProfileInfosActivity : AppCompatActivity() {

    var check = 0
    lateinit var imageUri : Uri
    var REQUEST_CODE = 100
    lateinit var propic: CircleImageView


    var database = FirebaseDatabase.getInstance("https://easyevent-5730d-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
    var auth = FirebaseAuth.getInstance()
    val uid = FirebaseAuth.getInstance().currentUser!!.uid
    val uidRef = database.child("users").child(uid)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_infos)

        var storageRef = FirebaseStorage.getInstance("gs://easyevent-5730d.appspot.com").reference.child("images/$uid")

        val emailText = findViewById<TextView>(R.id.email)
        val nameText = findViewById<TextView>(R.id.name)
        val surnameText = findViewById<TextView>(R.id.surname)
        val ageText = findViewById<TextView>(R.id.age)
        val save_button = findViewById<TextView>(R.id.save_button)
        val my_events_button = findViewById<TextView>(R.id.my_events_button)
        propic = findViewById<CircleImageView>(R.id.profile_image)

        imageUri = Uri.parse("android.resource://com.example.easyEvent/drawable/default_propic")


        val user = auth.currentUser


        emailText.text = "Email  -- > " + user?.email

        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                nameText.text =
                    "Firstname - - > " +  snapshot!!.child("name").getValue(String::class.java)
                surnameText.text =
                    "Surname - - > " +  snapshot.child("surname").getValue(String::class.java)

                ageText.text =
                    "Age- - > " + snapshot.child("age").getValue(Int::class.java)



            } else {
                Log.d("TAG", task.exception!!.message!!)
            }


            //Downalod original propic
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                Picasso.get().load(uri).into(propic)
                imageUri = uri
                }

            }

            //Change propic

            propic.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, REQUEST_CODE)

            }

        save_button.setOnClickListener{

            if(check != 0) {

                val uploadTask = storageRef.putFile(imageUri)
                uploadTask.addOnFailureListener {

                    Toast.makeText(
                        this@ProfileInfosActivity,
                        "Could not upload image!",
                        Toast.LENGTH_SHORT
                    ).show()

                }.addOnSuccessListener {

                    Toast.makeText(
                        this@ProfileInfosActivity,
                        "Profile picture uploaded successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }



        }

        my_events_button.setOnClickListener{

            val intent = Intent(this@ProfileInfosActivity, MyEventsActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }



        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            propic.setImageURI(data?.data) // handle chosen image
            imageUri = data?.data!!
            check = 1


        }



    }

}
