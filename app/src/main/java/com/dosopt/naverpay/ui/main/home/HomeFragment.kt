package com.dosopt.naverpay.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentHomeBinding
import com.dosopt.naverpay.domain.home.CardInfo
import com.dosopt.naverpay.domain.home.cardList
import com.dosopt.naverpay.domain.home.mockApiResponse
import com.dosopt.naverpay.ui.main.home.adapter.CardAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "_binding is  null" }

    private val selectedCardList = mutableListOf<CardInfo>()
    private lateinit var cardAdapter: CardAdapter
    private val defaultSelectedCardId = 1
    private lateinit var tvCardBalance: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTabMembership.setOnClickListener {
            selectTab(binding.tvTabMembership, binding.vTabMembershipBottom)
            deselectTab(binding.tvTabPayment, binding.vTapPayBottom)
        }

        binding.tvTabPayment.setOnClickListener {
            selectTab(binding.tvTabPayment, binding.vTapPayBottom)
            deselectTab(binding.tvTabMembership, binding.vTabMembershipBottom)
        }

        cardAdapter = CardAdapter { selectedCard ->
            if (selectedCard.id != 4) {
                handleCardSelection(selectedCard)
            }
        }

        val defaultSelectedCard = cardList.find { it.id == defaultSelectedCardId }
        defaultSelectedCard?.let { cardAdapter.setSelectedCard(it) }

        binding.rvCardList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = cardAdapter
        }
        cardAdapter.submitList(cardList)

        tvCardBalance = binding.tvCardBalance
        tvCardBalance.text = formatBalance(mockApiResponse.data.userPoint)

        binding.ivRecentPlace.load(mockApiResponse.data.onsitePayment.logoImgUrl.toInt()) {
            crossfade(true)
            error(R.drawable.img_recent_blank)
        }

        binding.tvRecentPlace.text = "${mockApiResponse.data.onsitePayment.name} ${mockApiResponse.data.onsitePayment.place}"

        binding.tvRecentPrice.text = "-${formatBalance(mockApiResponse.data.onsitePayment.amount)} " + getString(R.string.tv_recent_price_unit)

    }

    private fun selectTab(textView: TextView, bottomView: View) {
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.bg_white))
        bottomView.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.main_lightgreen
            )
        )
    }

    private fun deselectTab(textView: TextView, bottomView: View) {
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayscale_gray7))
        bottomView.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )
    }

    private fun handleCardSelection(selectedCard: CardInfo) {
        if (selectedCardList.contains(selectedCard)) {
            selectedCardList.remove(selectedCard)
        } else {
            selectedCardList.add(selectedCard)
        }

        cardAdapter.setSelectedCard(selectedCard)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun formatBalance(balance: Int): String {
        return String.format("%,d", balance)
    }
}
