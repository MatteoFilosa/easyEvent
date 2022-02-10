package com.example.easyEvent.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easyEvent.databinding.RecyclerViewItemBinding
import com.example.easyEvent.network.Event

class EventListAdapter(val clickListener: EventListener) : ListAdapter<Event,
        EventListAdapter.EventViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(clickListener, event)
    }

    class EventViewHolder(
        var binding: RecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: EventListener, event: Event) {
            binding.event = event
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(
            RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {

        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }
}

class EventListener(val clickListener: (event: Event) -> Unit) {
    fun onClick(event: Event) = clickListener(event)
}
