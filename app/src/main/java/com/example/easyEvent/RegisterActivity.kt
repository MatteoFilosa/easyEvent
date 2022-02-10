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
    //class User(val uid: String, val email:String, val password: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regBtn = findViewById<TextView>(R.id.register_button)
        val emailField = findViewById<TextView>(R.id.username_form_edit_text)
        val pswField = findViewById<TextView>(R.id.password_form_edit_text)
        val pswFieldConfirm = findViewById<TextView>(R.id.password_form_edit_text_confirm)
        regBtn.setOnClickListener {
            when {
                TextUtils.isEmpty(emailField.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(pswField.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter the password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                pswField.text.toString() != pswFieldConfirm.text.toString() -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "The passwords don't match!",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                else -> {

                    val email: String = emailField.text.toString().trim { it <= ' ' }
                    val psw: String = pswField.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, psw)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                //Registration correctly done
                                if (task.isSuccessful) {

                                    //Firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!


                                    /*Toast.makeText(
                                        this@RegisterActivity,
                                        "You have registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()*/

                                    val intent =
                                        Intent(this@RegisterActivity, UserInfosActivity::class.java)
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