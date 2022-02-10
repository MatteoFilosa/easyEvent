package com.example.easyEvent.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.easyEvent.MapsActivity
import com.example.easyEvent.databinding.FragmentEventDetailBinding


class EventDetailFragment : Fragment() {

    private val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentEventDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonViewMap.setOnClickListener {
            val latitude: String = viewModel.event.value?.location_lat!!
            val longitude: String = viewModel.event.value?.location_lon!!
            val title: String = viewModel.event.value?.title!!
            requireActivity().run {
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                intent.putExtra("title", title)
                startActivity(intent)
                finish()
            }
        }

        binding.buttonSendReservation.setOnClickListener {
            findNavController()
                .navigate(com.example.easyEvent.R.id.action_eventDetailFragment_to_eventReservedFragment)
        }

        return binding.root
    }
}