package com.dosopt.naverpay.ui.main.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemPlacePaymentBinding
import com.dosopt.naverpay.domain.model.place.OnsitepaymentList
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PlacePaymentAdapter :
    ListAdapter<OnsitepaymentList, PlacePaymentAdapter.PlacePaymentViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacePaymentViewHolder {
        val binding =
            ItemPlacePaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacePaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacePaymentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PlacePaymentViewHolder(private val binding: ItemPlacePaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: OnsitepaymentList) {
            binding.ivDomino.load(data.logo_img_url)
            binding.tvDomino.text = data.name
        }
    }

    companion object {
        private val diffCallback = ItemDiffCallback<OnsitepaymentList>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
