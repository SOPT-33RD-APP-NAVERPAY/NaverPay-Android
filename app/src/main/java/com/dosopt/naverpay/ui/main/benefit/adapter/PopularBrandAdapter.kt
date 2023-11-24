package com.dosopt.naverpay.ui.main.benefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemBenefitPopularBrandBinding
import com.dosopt.naverpay.domain.model.benefit.Brand
import com.dosopt.naverpay.util.view.ItemDiffCallback

class PopularBrandAdapter(
    private val onLikeClicked: (Brand) -> Unit
) : ListAdapter<Brand, PopularBrandAdapter.PopularBrandViewHolder>(
    ItemDiffCallback<Brand>(
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
        private val onLikeClicked: (Brand) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(brand: Brand) {
            setBrandData(brand)
            setLikeStatus(brand)
            setLikeClickListener(brand)
        }

        private fun setBrandData(brand: Brand) {
            with(binding) {
                tvBenefitPopularRank.text = brand.id.toString()
                ivBenefitPopularLogo.load(brand.logo_img_url)
                tvBenefitPopularName.text = brand.name
                tvBenefitPopularContent.text = brand.discount_content
                tvBenefitPopularType.text = brand.discount_type
            }
            if (brand.id == 1L) {
                binding.tvBenefitPopularRank.setTextColor(
                    binding.root.context.getColor(R.color.main_green)
                )
            }
        }

        private fun setLikeStatus(brand: Brand) {
            binding.ibBenefitPopularLike.setBackgroundResource(
                if (brand.is_brand_like) R.drawable.ic_like_on else R.drawable.ic_like_off
            )
        }

        private fun setLikeClickListener(brand: Brand) {
            binding.ibBenefitPopularLike.setOnClickListener {
                onLikeClicked(brand)
            }
        }
    }
}
