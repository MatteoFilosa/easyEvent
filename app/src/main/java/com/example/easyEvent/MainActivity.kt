package com.example.easyEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyEvent.compose.EventCard
import com.example.easyEvent.model.EventModel
import com.example.easyEvent.viewmodel.EventViewModel
import com.google.android.material.composethemeadapter.MdcTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vm: EventViewModel
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* val userDisplayed = findViewById<TextView>(R.id.user)
        val editprofile_btn = findViewById<TextView>(R.id.editprofile_button)

        val mainComposeView = findViewById<ComposeView>(R.id.main_compose_view)

        val email = intent.getStringExtra("email_id")
        val userId = intent.getStringExtra("user_id")
        userDisplayed.text = "email :: $email"


        editprofile_btn.setOnClickListener{
            val intent = Intent(this@MainActivity, ProfileInfosActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        
        mainComposeView.setContent {
            MdcTheme {
                EventCard("Test")
            }
        } */

        vm = ViewModelProvider(this)[EventViewModel::class.java]

        initAdapter()

        vm.fetchAllEvents()

        vm.eventModelListLiveData?.observe(this, Observer {
            if(it != null) {
                rv_home.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<EventModel>)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
            progress_home.visibility = View.GONE
        })

    }

    private fun initAdapter() {
        adapter = EventAdapter()
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter
    }
}