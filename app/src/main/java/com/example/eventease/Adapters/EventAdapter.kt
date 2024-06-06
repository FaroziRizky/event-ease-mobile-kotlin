package com.example.eventease.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.eventease.DetailEventActivity.DetailEventPage
import com.example.eventease.Models.ItemEventModel
import com.example.eventease.R
import com.squareup.picasso.Picasso

class EventAdapter(private var eventList: List<ItemEventModel>) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event_recycle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val event = eventList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val orgName: TextView = itemView.findViewById(R.id.orgName)
        private val eventName: TextView = itemView.findViewById(R.id.eventName)
        private val eventType: TextView = itemView.findViewById(R.id.eventType)
        private val eventStatus: TextView = itemView.findViewById(R.id.eventStatus)
        private val eventImage: ImageView = itemView.findViewById(R.id.eventImage)



        fun bind(event: ItemEventModel) {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailEventPage::class.java).apply {
                    putExtra("idEvent", event.idEvent)
                }
                itemView.context.startActivity(intent)

            }


            orgName.text = event.organizationName
            eventName.text = event.eventName
            eventType.text = event.eventType
            eventStatus.text = event.eventStatus


            // Load image using Picasso
            Picasso.get()
                .load(event.eventImage)
                .fit()
                .centerCrop()
                .into(eventImage)
        }
    }

    fun getAllEvents(): List<ItemEventModel> {
        return eventList
    }

    fun updateEvents(newEvents: List<ItemEventModel>) {
        eventList = newEvents
        notifyDataSetChanged()
    }


}
