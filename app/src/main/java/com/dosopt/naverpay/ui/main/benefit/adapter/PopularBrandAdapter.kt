package com.dosopt.naverpay.ui.main.benefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemBenefitPopularBrandBinding
import com.dosopt.naverpay.domain.model.benefit.BenefitBrand
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PopularBrandAdapter(
    private val onLikeClicked: (BenefitBrand) -> Unit
) : ListAdapter<BenefitBrand, PopularBrandAdapter.PopularBrandViewHolder>(
    ItemDiffCallback<BenefitBrand>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularBrandViewHolder {
        val binding = ItemBenefitPopularBrandBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularBrandViewHolder(binding, onLikeClicked)
    }

    override fun onBindViewHolder(holder: PopularBrandViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PopularBrandViewHolder(
        private val binding: ItemBenefitPopularBrandBinding,
        private val onLikeClicked: (BenefitBrand) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(benefitBrand: BenefitBrand) {
            setBrandData(benefitBrand)
            setLikeStatus(benefitBrand)
            setLikeClickListener(benefitBrand)
        }

        private fun setBrandData(benefitBrand: BenefitBrand) {
            with(binding) {
                tvBenefitPopularRank.text = benefitBrand.id.toString()
                ivBenefitPopularLogo.load(benefitBrand.logo_img_url)
                tvBenefitPopularName.text = benefitBrand.name
                tvBenefitPopularContent.text = benefitBrand.discount_content
                tvBenefitPopularType.text = benefitBrand.discount_type
            }
            if (benefitBrand.id == 1L) {
                binding.tvBenefitPopularRank.setTextColor(
                    binding.root.context.getColor(R.color.main_green)
                )
            }
        }

        private fun setLikeStatus(benefitBrand: BenefitBrand) {
            binding.ibBenefitPopularLike.setBackgroundResource(
                if (benefitBrand.is_brand_like) R.drawable.ic_like_on else R.drawable.ic_like_off
            )
        }

        private fun setLikeClickListener(benefitBrand: BenefitBrand) {
            binding.ibBenefitPopularLike.setOnClickListener {
                onLikeClicked(benefitBrand)
            }
        }
    }
}
