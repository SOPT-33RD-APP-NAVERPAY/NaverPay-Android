package com.dosopt.naverpay.ui.main.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemPlaceRecommendBinding
import com.dosopt.naverpay.domain.model.place.BrandList
import com.dosopt.naverpay.network.dto.PlaceResponse
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PlaceRecommendAdapter :
    ListAdapter<PlaceResponse.BrandListDto, PlaceRecommendAdapter.PlaceRecommendViewHolder>(
        diffCallback
    ) {

    private var brandList: List<PlaceResponse.BrandListDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceRecommendViewHolder {
        val binding =
            ItemPlaceRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceRecommendViewHolder, position: Int) {
        val place = brandList[position]
        holder.onBind(place)
    }

    class PlaceRecommendViewHolder(private val binding: ItemPlaceRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlaceResponse.BrandListDto) {
            binding.ivMusinsa.load(data.logoImgUrl)
        }
    }

    companion object {
        private val diffCallback = ItemDiffCallback<PlaceResponse.BrandListDto>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

