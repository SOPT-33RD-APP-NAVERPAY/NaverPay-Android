package com.dosopt.naverpay.ui.main.point.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemPointRewardBinding
import com.dosopt.naverpay.network.dto.PointResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PointAdapter : RecyclerView.Adapter<PointRewardViewHolder>() {

    private var pointRewardList: List<PointResponse.BrandListDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointRewardViewHolder {
        val binding =
            ItemPointRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointRewardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointRewardViewHolder, position: Int) {
        val brand = pointRewardList[position]
        holder.onBind(brand)
    }

    override fun getItemCount(): Int = pointRewardList.size

    fun submitList(list: List<PointResponse.BrandListDto>) {
        val diffResult = calculateDiff(PointRewardListDiffCallback(pointRewardList, list))
        pointRewardList = list
        diffResult.dispatchUpdatesTo(this)
    }
}

class PointRewardListDiffCallback(
    private val oldList: List<PointResponse.BrandListDto>,
    private val newList: List<PointResponse.BrandListDto>
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

class PointRewardViewHolder(
    private val binding: ItemPointRewardBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(brand: PointResponse.BrandListDto) {
        with(binding) {
            tvPointRewardName.text = brand.name
            tvPointRewardAmount.text = itemView.context.getString(R.string.tv_point_reward_amount, brand.saving)
            val formattedDate = formatPaymentDate(brand.paymentDate)
            val formattedTime = formatPaymentTime(brand.paymentDate)
            tvPointRewardDate.text = formattedDate
            tvPointRewardTime.text = formattedTime
        }
    }

    private fun formatPaymentDate(paymentDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val parsedDate = LocalDateTime.parse(paymentDate, formatter)
        return parsedDate.format(DateTimeFormatter.ofPattern("MM.dd"))
    }

    private fun formatPaymentTime(paymentDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val parsedDate = LocalDateTime.parse(paymentDate, formatter)
        return parsedDate.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}