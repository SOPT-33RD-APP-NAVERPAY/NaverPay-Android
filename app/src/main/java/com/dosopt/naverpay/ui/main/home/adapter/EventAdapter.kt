package com.dosopt.naverpay.ui.main.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemEventBinding
import com.dosopt.naverpay.domain.model.home.EventInfo

class EventAdapter(private val eventList: List<EventInfo>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEventBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(eventInfo: EventInfo) {
            with(binding) {
                ivEventBackground.setBackgroundColor(Color.parseColor(eventInfo.background))
                ivEventLogo.load(eventInfo.logo) {
                    crossfade(true)
                    error(R.drawable.circle_bg_white)
                }
                tvEventDetail.text = eventInfo.detail
                tvEventPercentage.text = eventInfo.percentage
            }
        }
    }
}