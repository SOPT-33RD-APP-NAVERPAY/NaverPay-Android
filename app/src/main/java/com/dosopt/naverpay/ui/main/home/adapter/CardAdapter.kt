package com.dosopt.naverpay.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ItemHomeCardDeselectedBinding
import com.dosopt.naverpay.databinding.ItemHomeCardSelectedBinding
import com.dosopt.naverpay.domain.model.home.CardInfo
import com.dosopt.naverpay.util.view.ItemDiffCallback

class CardAdapter(
    private val onCardClickListener: (CardInfo) -> Unit,
) : ListAdapter<CardInfo, RecyclerView.ViewHolder>(
    ItemDiffCallback<CardInfo>(
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    private var selectedCard: CardInfo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SELECTED_CARD_VIEW_TYPE -> {
                val binding = ItemHomeCardSelectedBinding.inflate(inflater, parent, false)
                SelectedCardViewHolder(binding, onCardClickListener)
            }

            DESELECTED_CARD_VIEW_TYPE -> {
                val binding = ItemHomeCardDeselectedBinding.inflate(inflater, parent, false)
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
            val oldSelectedCard = selectedCard
            selectedCard = card

            if (oldSelectedCard != null && card != null) {
                val oldSelectedCardIndex = currentList.indexOf(oldSelectedCard)
                val newSelectedCardIndex = currentList.indexOf(card)

                if (oldSelectedCardIndex != newSelectedCardIndex) {
                    notifyItemChanged(oldSelectedCardIndex)
                    notifyItemChanged(newSelectedCardIndex)

                }
            }
        }
    }

    companion object {
        private const val SELECTED_CARD_VIEW_TYPE = 0
        private const val DESELECTED_CARD_VIEW_TYPE = 1
    }
}

class SelectedCardViewHolder(
    private val binding: ItemHomeCardSelectedBinding,
    private val onCardClickListener: (CardInfo) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(cardInfo: CardInfo) {
        binding.ivCardSelected.load(cardInfo.img) {
            crossfade(true)
            error(R.drawable.rectangle_bg_white_radius_6)
            transformations(RoundedCornersTransformation(radius = 8f))
        }
        binding.root.setOnClickListener { onCardClickListener(cardInfo) }
    }
}

class DeselectedCardViewHolder(
    private val binding: ItemHomeCardDeselectedBinding,
    private val onCardClickListener: (CardInfo) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(cardInfo: CardInfo) {
        binding.ivCardDeselected.load(cardInfo.img) {
            crossfade(true)
            error(R.drawable.rectangle_bg_white_radius_6)
            transformations(RoundedCornersTransformation(radius = 8f))
        }
        binding.root.setOnClickListener { onCardClickListener(cardInfo) }
    }
}