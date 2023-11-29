package com.dosopt.naverpay.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemHomeBrandBinding
import com.dosopt.naverpay.network.dto.HomeResponse

class BrandAdapter : RecyclerView.Adapter<BrandViewHolder>() {

    private var brandListDto: List<HomeResponse.BrandListDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding =
            ItemHomeBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand = brandListDto[position]
        holder.onBind(brand)
    }

    override fun getItemCount(): Int = brandListDto.size

    fun submitList(list: List<HomeResponse.BrandListDto>) {
        val diffResult = DiffUtil.calculateDiff(BrandListDiffCallback(brandListDto, list))
        brandListDto = list
        diffResult.dispatchUpdatesTo(this)
    }
}

class BrandListDiffCallback(
    private val oldList: List<HomeResponse.BrandListDto>,
    private val newList: List<HomeResponse.BrandListDto>,
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
    private val binding: ItemHomeBrandBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(Brand: HomeResponse.BrandListDto) {
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