package com.dosopt.naverpay.ui.main.benefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.databinding.ItemBenefitImmediateBrandBinding
import com.dosopt.naverpay.domain.model.benefit.BenefitBrand
import com.dosopt.naverpay.util.view.ItemDiffCallback

class ImmediateBrandAdapter : ListAdapter<BenefitBrand, ImmediateBrandAdapter.ImmediateBrandViewHolder>(
    ItemDiffCallback<BenefitBrand>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImmediateBrandViewHolder {
        val binding =
            ItemBenefitImmediateBrandBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ImmediateBrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImmediateBrandViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ImmediateBrandViewHolder(private val binding: ItemBenefitImmediateBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(benefitBrand: BenefitBrand) {
            with(binding) {
                ivBenefitImmediateLogo.load(benefitBrand.logo_img_url)
                tvBenefitImmediateName.text = benefitBrand.name
                tvBenefitImmediateContent.text = benefitBrand.discount_content
                tvBenefitImmediateType.text = benefitBrand.discount_type
            }
        }
    }
}