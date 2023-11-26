package com.dosopt.naverpay.ui.main.benefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemBenefitAdmobBinding
import com.dosopt.naverpay.util.view.ItemDiffCallback

class AdmobAdapter : ListAdapter<Int, AdmobAdapter.AdmobImageViewHolder>(
    ItemDiffCallback<Int>(
        onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdmobImageViewHolder {
        val binding = ItemBenefitAdmobBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AdmobImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdmobImageViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class AdmobImageViewHolder(private val binding: ItemBenefitAdmobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageRes: Int) {
            binding.ivBenefitAd.load(imageRes)
        }
    }
}
