package com.example.easyEvent.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easyEvent.MainActivity
import com.example.easyEvent.databinding.FragmentEventReservedBinding

class EventReservedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentEventReservedBinding.inflate(inflater)

        binding.buttonHomePage.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        return binding.root
    }
}

