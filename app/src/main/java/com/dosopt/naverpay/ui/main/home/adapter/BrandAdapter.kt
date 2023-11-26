package com.dosopt.naverpay.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemBrandBinding
import com.dosopt.naverpay.domain.home.Brand

class BrandAdapter(private val brandList: List<Brand>) : RecyclerView.Adapter<BrandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = brandList[position]
        holder.onBind(brand)
    }

    override fun getItemCount(): Int = brandList.size
}

class BrandViewHolder(
    private val binding: ItemBrandBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(Brand: Brand) {
        with(binding) {
            binding.ivBrandLogo.load(Brand.logoImgUrl) {
                crossfade(true)
                error(R.drawable.img_brand_blank)
            }
            tvBrandName.text = Brand.name
            tvBrandDiscount.text = Brand.discountContent
        }
    }
}