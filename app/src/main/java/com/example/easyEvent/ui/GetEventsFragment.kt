package com.example.easyEvent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.easyEvent.R
import com.example.easyEvent.databinding.FragmentGetEventsBinding

class GetEventsFragment : Fragment() {

    private val viewModel: EventViewModel by activityViewModels()
    private lateinit var cityArg: String

    companion object {
        fun newInstance() = GetEventsFragment
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cityArg = activity?.intent?.getStringExtra("city").toString()

        val binding = FragmentGetEventsBinding.inflate(inflater)

        viewModel.getEventList(cityArg)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = EventListAdapter(EventListener { event ->
            viewModel.onEventClicked(event)
            findNavController()
                .navigate(R.id.action_getEventsFragment_to_eventDetailFragment)
        })

        return binding.root
    }


}