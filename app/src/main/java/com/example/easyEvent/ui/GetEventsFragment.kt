package com.example.easyEvent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.easyEvent.R
import com.example.easyEvent.databinding.FragmentEventListBinding

class GetEventsFragment: Fragment() {

    private val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventListBinding.inflate(inflater)

        viewModel.getEventList("Rome")

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = EventListAdapter(EventListener { event ->
            viewModel.onEventClicked(event)
            findNavController()
                .navigate(R.id.action_eventListFragment_to_eventDetailFragment)
        })

        return binding.root
    }

    companion object {
        fun newInstance() = GetEventsFragment
    }

}