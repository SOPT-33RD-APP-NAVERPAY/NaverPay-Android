package com.dosopt.naverpay.ui.main.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemPlaceNearbyBinding
import com.dosopt.naverpay.domain.model.place.NearbyplaceList
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PlaceNearbyAdapter :
    ListAdapter<NearbyplaceList, PlaceNearbyAdapter.PlaceNearbyViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceNearbyViewHolder {
        val binding =
            ItemPlaceNearbyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceNearbyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceNearbyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PlaceNearbyViewHolder(private val binding: ItemPlaceNearbyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NearbyplaceList) {
            binding.ivCu.load(data.logo_img_url)
            binding.tvCu.text = data.name
            binding.tvDisCu.text = data.distanceValue
        }
    }

    companion object {
        private val diffCallback = ItemDiffCallback<NearbyplaceList>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
