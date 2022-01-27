package com.example.easyEvent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.easyEvent.R
import androidx.recyclerview.widget.RecyclerView
import com.example.easyEvent.databinding.HomeRvItemViewBinding
import com.example.easyEvent.model.EventModel
import androidx.appcompat.app.AppCompatActivity

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var data: ArrayList<EventModel>? = null

    fun setData(list: ArrayList<EventModel>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_rv_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(item: EventModel?) {
            val  tvHomeItemTitle = itemView.findViewById<TextView>(R.id.tv_home_item_title)
            val tvHomeItemBody = itemView.findViewById<TextView>(R.id.tv_home_item_body)
            tvHomeItemTitle.text = item?.title
            tvHomeItemBody.text = item?.description
        }
    }
}