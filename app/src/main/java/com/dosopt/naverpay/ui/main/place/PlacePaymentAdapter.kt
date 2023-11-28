package com.dosopt.naverpay.ui.main.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemPlacePaymentBinding
import com.dosopt.naverpay.network.dto.PlaceResponse
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PlacePaymentAdapter :
    ListAdapter<PlaceResponse.OnsitepaymentListDto, PlacePaymentAdapter.PlacePaymentViewHolder>(
        diffCallback
    ) {

    private var onsitepaymentList: List<PlaceResponse.OnsitepaymentListDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacePaymentViewHolder {
        val binding =
            ItemPlacePaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlacePaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacePaymentViewHolder, position: Int) {
        val place = onsitepaymentList[position]
        holder.onBind(place)
    }

    class PlacePaymentViewHolder(private val binding: ItemPlacePaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlaceResponse.OnsitepaymentListDto) {
            binding.ivDomino.load(data.logoImgUrl)
            binding.tvDomino.text = data.name
        }
    }

    companion object {
        private val diffCallback = ItemDiffCallback<PlaceResponse.OnsitepaymentListDto>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
