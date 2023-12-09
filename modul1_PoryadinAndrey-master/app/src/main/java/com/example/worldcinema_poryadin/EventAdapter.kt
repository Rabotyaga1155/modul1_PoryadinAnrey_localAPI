package com.example.worldcinema_poryadin

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var events: List<Event> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun submitList(newList: List<Event>) {
        val diffResult = DiffUtil.calculateDiff(EventDiffCallback(events, newList))
        events = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewEventName)
        private val dateTextView: TextView = itemView.findViewById(R.id.textViewEventDate)

        fun bind(event: Event) {
            nameTextView.text = event.name
            dateTextView.text = event.eventDate
        }
    }

    private class EventDiffCallback(private val oldList: List<Event>, private val newList: List<Event>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
