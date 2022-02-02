package com.example.easyEvent.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.easyEvent.databinding.RecyclerViewItemBinding
import com.example.easyEvent.network.Event

class EventListAdapter : ListAdapter<Event,
        EventListAdapter.EventViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class EventViewHolder(private var binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(Event: Event) {
            binding.event = Event
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {

        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
