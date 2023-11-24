package com.dosopt.naverpay.ui.main.benefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemBenefitCardBinding
import com.dosopt.naverpay.util.view.ItemDiffCallback

class CardMenuAdapter : ListAdapter<Int, CardMenuAdapter.CardViewHolder>(
    ItemDiffCallback<Int>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        val binding =
            ItemBenefitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class CardViewHolder(private val binding: ItemBenefitCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageRes: Int) {
            with(binding) {
                ivBenefitBtnCard.load(imageRes)
            }
        }
    }

}