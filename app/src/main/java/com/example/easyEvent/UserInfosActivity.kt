package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class UserInfosActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_infos)

        var namefield = findViewById<TextView>(R.id.name_form_edit_text)
        var surnamefield = findViewById<TextView>(R.id.surname_form_edit_text)
        var agefield = findViewById<TextView>(R.id.age_form_edit_text)
        var button = findViewById<Button>(R.id.submit_button)
        var genderfield = findViewById<RadioButton>(R.id.female_button)
        //var imageField = findViewById<CircleImageView>(R.id.profile_image)

        var database = FirebaseDatabase.getInstance("https://easyevent-5730d-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
        val uid = intent.getStringExtra("user_id").toString()

        //imageField.setOnClickListener(){
            //val intent = Intent(Intent.ACTION_PICK)
            //intent.type = "image/*"
            //startActivityForResult(intent, 0)
        //}

        button.setOnClickListener {


            if((namefield.text.toString().isEmpty()) or (namefield.text.toString().contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()))) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter a valid name.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else if((surnamefield.text.toString().isEmpty()) or (surnamefield.text.toString().contains("[0-9!\"#$%&()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()))) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter a valid surname.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else if(agefield.text.toString().isEmpty()) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter your age.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else if(agefield.text.toString().toInt()>=120) {

                Toast.makeText(
                    this@UserInfosActivity,
                    "Enter a valid age.",
                    Toast.LENGTH_SHORT
                ).show()
            }


            else{

                var name = namefield.text.toString()
                var surname = surnamefield.text.toString()
                var age = agefield.text.toString().toInt()
                var gender ="M"

                if (genderfield.isChecked){
                     gender ="F"
                }


                database.child("users").child(uid).setValue(User(name, surname, age, gender))
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@UserInfosActivity,
                            "Registration completed!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@UserInfosActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
}

