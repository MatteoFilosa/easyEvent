package com.example.easyEvent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Thread.sleep
import java.net.URI
import java.util.*

class UserInfosActivity : AppCompatActivity() {

    var check = 0
    lateinit var imageUri : Uri
    lateinit var imageField: CircleImageView
    var REQUEST_CODE = 100



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_infos)

        imageUri = Uri.parse("android.resource://com.example.easyEvent/drawable/default_propic");
        var namefield = findViewById<TextView>(R.id.name_form_edit_text)
        var surnamefield = findViewById<TextView>(R.id.surname_form_edit_text)
        var agefield = findViewById<TextView>(R.id.age_form_edit_text)
        var button = findViewById<Button>(R.id.submit_button)
        var genderfield = findViewById<RadioButton>(R.id.female_button)
        imageField = findViewById<CircleImageView>(R.id.profile_image)
        var storageRef = FirebaseStorage.getInstance("gs://easyevent-5730d.appspot.com").getReference()



        var database =
            FirebaseDatabase.getInstance("https://easyevent-5730d-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference()
        val uid = intent.getStringExtra("user_id").toString() //forse non conviene prenderlo dall'intent ma dall'auth

        imageField.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)

        }



        button.setOnClickListener {


            if ((namefield.text.toString().isEmpty()) or (namefield.text.toString()
                    .contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()))
            ) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter a valid name.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if ((surnamefield.text.toString().isEmpty()) or (surnamefield.text.toString()
                    .contains("[0-9!\"#$%&()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()))
            ) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter a valid surname.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (agefield.text.toString().isEmpty()) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter your age.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if ((agefield.text.toString().toInt() >= 120) or (agefield.text.toString()
                    .toInt() == 0)
            ) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter a valid age.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                var name = namefield.text.toString()
                var surname = surnamefield.text.toString()
                var age = agefield.text.toString().toInt()
                var gender = "M"

                if (genderfield.isChecked) {
                    gender = "F"
                }

                if(check != 0) {

                    val imagesRef = storageRef.child("images/$uid")
                    val uploadTask = imagesRef.putFile(imageUri)
                    sleep(2000) //serve per dare tempo di caricare la foto senno nell intent dopo non la fa vede

                    Toast.makeText(
                        this@UserInfosActivity,
                        "Uploading profile picture...",
                        Toast.LENGTH_SHORT
                    ).show()

                    uploadTask.addOnFailureListener {

                        Toast.makeText(
                            this@UserInfosActivity,
                            "Could not upload image!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }.addOnSuccessListener {

                        Toast.makeText(
                            this@UserInfosActivity,
                            "Profile picture uploaded successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


                database.child("users").child(uid).setValue(User(name, surname, age, gender))
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@UserInfosActivity,
                            "Registration completed!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@UserInfosActivity, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this@UserInfosActivity,
                            "An unknown error occurred.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageField.setImageURI(data?.data) // handle chosen image
            imageUri = data?.data!!
            check = 1

        }

    }
}


