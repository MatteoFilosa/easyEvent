package com.example.easyEvent.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.easyEvent.databinding.FragmentEventDetailBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EventDetailFragment : Fragment() {

    private val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentEventDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val fab: ExtendedFloatingActionButton = binding.fabViewMap
        fab.setOnClickListener {
            Toast.makeText(this.context, "Viewing on map...", Toast.LENGTH_SHORT)
        }

        return binding.root
    }
}