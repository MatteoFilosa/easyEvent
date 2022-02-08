package com.example.easyEvent

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyEvent.ui.Adapters
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lateinit var adapters: Adapters

        var list = mutableListOf<Int>()

        list.add(R.drawable.one1)
        list.add(R.drawable.two2)
        list.add(R.drawable.three3)

        adapters=Adapters(this)
        adapters.setContentList(list)
        viewpager.adapter=adapters

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

                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)

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