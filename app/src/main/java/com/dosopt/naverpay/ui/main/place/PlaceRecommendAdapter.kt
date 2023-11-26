package com.dosopt.naverpay.ui.main.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemPlaceRecommendBinding
import com.dosopt.naverpay.domain.place.BrandList
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PlaceRecommendAdapter :
    ListAdapter<BrandList, PlaceRecommendAdapter.PlaceRecommendViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceRecommendViewHolder {
        val binding =
            ItemPlaceRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceRecommendViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PlaceRecommendViewHolder(private val binding: ItemPlaceRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BrandList) {
            binding.ivMusinsa.load(data.logo_img_url)
        }
    }

    companion object {
        private val diffCallback = ItemDiffCallback<BrandList>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

