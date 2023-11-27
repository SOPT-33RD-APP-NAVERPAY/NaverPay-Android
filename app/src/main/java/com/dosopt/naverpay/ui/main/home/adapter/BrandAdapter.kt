package com.dosopt.naverpay.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemBrandBinding
import com.dosopt.naverpay.domain.model.home.BrandInfo
import com.dosopt.naverpay.domain.model.home.CardInfo
import com.dosopt.naverpay.network.dto.HomeResponse
import com.dosopt.naverpay.util.view.ItemDiffCallback

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
        val newList = list.map { BrandInfo(it.id, it.name, it.place, it.logoImgUrl, it.discountContent) }
        val diffResult = calculateDiff(BrandListDiffCallback(brandList, newList))
        brandList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}

class BrandListDiffCallback(
    private val oldList: List<BrandInfo>,
    private val newList: List<BrandInfo>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

class BrandViewHolder(
    private val binding: ItemBrandBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(Brand: BrandInfo) {
        with(binding) {
            binding.ivBrandLogo.load(Brand.logoImgUrl) {
                crossfade(true)
                error(R.drawable.rectangle_bg_white_radius_6)
            }
            tvBrandName.text = Brand.name
            tvBrandDiscount.text = Brand.discountContent
        }
    }
}