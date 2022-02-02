package com.example.easyEvent.overview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.easyEvent.R
import com.example.easyEvent.databinding.FragmentOverviewBinding

class OverviewActivity : AppCompatActivity() {

    private val viewModel: OverviewViewModel by viewModels()
    lateinit var forwardButtonView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        OverviewFragment.newInstance()
    }
}