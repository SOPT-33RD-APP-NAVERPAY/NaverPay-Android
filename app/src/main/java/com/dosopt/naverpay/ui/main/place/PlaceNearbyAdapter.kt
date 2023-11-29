package com.dosopt.naverpay.ui.main.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemPlaceNearbyBinding
import com.dosopt.naverpay.network.dto.PlaceResponse
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PlaceNearbyAdapter :
    ListAdapter<PlaceResponse.NearbyplaceListDto, PlaceNearbyAdapter.PlaceNearbyViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceNearbyViewHolder {
        val binding =
            ItemPlaceNearbyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceNearbyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceNearbyViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class PlaceNearbyViewHolder(private val binding: ItemPlaceNearbyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlaceResponse.NearbyplaceListDto) {
            binding.ivCu.load(data.logoImgUrl)
            binding.tvCu.text = data.name
            binding.tvDisCu.text = data.distanceValue
        }
    }

    companion object {
        private val diffCallback = ItemDiffCallback<PlaceResponse.NearbyplaceListDto>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
