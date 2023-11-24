package com.dosopt.naverpay.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemCardDeselectedBinding
import com.dosopt.naverpay.databinding.ItemCardSelectedBinding
import com.dosopt.naverpay.domain.home.CardInfo

class CardAdapter(
    private val onCardClickListener: (CardInfo) -> Unit,
) : ListAdapter<CardInfo, RecyclerView.ViewHolder>(CardDiffCallback()) {

    private var selectedCard: CardInfo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SELECTED_CARD_VIEW_TYPE -> {
                val binding = ItemCardSelectedBinding.inflate(inflater, parent, false)
                SelectedCardViewHolder(binding, onCardClickListener)
            }

            DESELECTED_CARD_VIEW_TYPE -> {
                val binding = ItemCardDeselectedBinding.inflate(inflater, parent, false)
                DeselectedCardViewHolder(binding, onCardClickListener)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SelectedCardViewHolder -> holder.onBind(getItem(position))
            is DeselectedCardViewHolder -> holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == selectedCard) SELECTED_CARD_VIEW_TYPE else DESELECTED_CARD_VIEW_TYPE
    }

    fun setSelectedCard(card: CardInfo?) {
        if (card != selectedCard) {
            // 선택된 카드가 변경된 경우에만 갱신
            val oldSelectedCard = selectedCard
            selectedCard = card

            // 변경된 부분만 갱신
            if (oldSelectedCard != null && card != null) {
                notifyItemChanged(currentList.indexOf(oldSelectedCard))
                notifyItemChanged(currentList.indexOf(card))
            } else {
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val SELECTED_CARD_VIEW_TYPE = 0
        private const val DESELECTED_CARD_VIEW_TYPE = 1
    }
}

class SelectedCardViewHolder(
    private val binding: ItemCardSelectedBinding,
    private val onCardClickListener: (CardInfo) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(cardInfo: CardInfo) {
        binding.ivCardSelected.load(cardInfo.img) {
            crossfade(true)
            error(R.drawable.img_card_blank)
        }
        binding.root.setOnClickListener { onCardClickListener(cardInfo) }
    }
}

class DeselectedCardViewHolder(
    private val binding: ItemCardDeselectedBinding,
    private val onCardClickListener: (CardInfo) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(cardInfo: CardInfo) {
        binding.ivCardDeselected.load(cardInfo.img) {
            crossfade(true)
            error(R.drawable.img_card_blank)
        }
        binding.root.setOnClickListener { onCardClickListener(cardInfo) }
    }
}

class CardDiffCallback : DiffUtil.ItemCallback<CardInfo>() {
    override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem == newItem
    }
}