package com.example.easyEvent

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val reg_btn = findViewById<TextView>(R.id.register_button)
        val email_field = findViewById<TextView>(R.id.username_form_edit_text)
        val psw_field = findViewById<TextView>(R.id.password_form_edit_text)
        reg_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(email_field.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(psw_field.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter the password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = email_field.text.toString().trim { it <= ' '}
                    val psw: String = psw_field.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,psw)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                //Registration correctly done
                                if(task.isSuccessful) {

                                    //Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "You are registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this@RegisterActivity, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //If registering is not successful, then show error message.
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()


                                }
                            }
                        )


                }


            }
        }
    }
}