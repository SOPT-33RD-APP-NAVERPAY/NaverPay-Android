package com.dosopt.naverpay.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemBrandBinding
import com.dosopt.naverpay.domain.model.home.BrandInfo
import com.dosopt.naverpay.network.dto.HomeResponse

class BrandAdapter : RecyclerView.Adapter<BrandViewHolder>() {

    private var brandList: List<BrandInfo> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = brandList[position]
        holder.onBind(brand)
    }

    override fun getItemCount(): Int = brandList.size

    fun submitList(list: List<HomeResponse.BrandListDto>) {
        brandList = list.map { BrandInfo(it.id, it.name, it.place, it.logoImgUrl, it.discountContent) }
        notifyDataSetChanged()
    }
}

class BrandViewHolder(
    private val binding: ItemBrandBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(Brand: BrandInfo) {
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