package com.example.easyEvent.overview

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.example.easyEvent.R
import com.example.easyEvent.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()
    lateinit var forwardButtonView : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)
        // val binding = RecyclerViewItemBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        binding.eventsList.adapter = EventListAdapter()

        return binding.root
    }

    companion object {
        fun newInstance() = OverviewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forwardButtonView = view.findViewById<ImageView>(R.id.forward_button)
        forwardButtonView.setOnClickListener {
            Toast.makeText(this.context, "Forward button pressed", Toast.LENGTH_SHORT)
        }
    }
}