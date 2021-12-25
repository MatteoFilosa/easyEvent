package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val log_btn = findViewById<TextView>(R.id.login_button)
        val email_field = findViewById<TextView>(R.id.username_form_edit_text)
        val psw_field = findViewById<TextView>(R.id.password_form_edit_text)
        val reg_btn = findViewById<TextView>(R.id.register_button)
        val forgot = findViewById<TextView>(R.id.forgot_password_text)

        reg_btn.setOnClickListener{

            startActivity(Intent(this, RegisterActivity::class.java))
        }

        forgot.setOnClickListener{
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }

        log_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(email_field.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter the email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(psw_field.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter the password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = email_field.text.toString().trim { it <= ' '}
                    val psw: String = psw_field.text.toString().trim { it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,psw)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                //Login correctly done
                                if(task.isSuccessful) {


                                    Toast.makeText(
                                        this@LoginActivity,
                                        "You have logged in successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //If logging in is not successful, then show error message.
                                    Toast.makeText(
                                        this@LoginActivity,
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