package com.dosopt.naverpay.ui.main.home

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
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
import com.dosopt.naverpay.domain.home.eventList
import com.dosopt.naverpay.domain.home.mockApiResponse
import com.dosopt.naverpay.ui.main.home.adapter.CardAdapter
import com.dosopt.naverpay.ui.main.home.adapter.EventAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "_binding is  null" }

    private val selectedCardList = mutableListOf<CardInfo>()
    private lateinit var cardAdapter: CardAdapter
    private lateinit var eventAdapter: EventAdapter
    private val defaultSelectedCardId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabs()
        setupCardRecyclerView()
        setupEventRecyclerView()
        setupTitleColor()
        setupRecentPayment()

    }

    private fun setupTabs() {
        with(binding) {
            tvTabMembership.setOnClickListener {
                selectTab(tvTabMembership, vTabMembershipBottom)
                deselectTab(tvTabPayment, vTapPayBottom)
            }

            tvTabPayment.setOnClickListener {
                selectTab(tvTabPayment, vTapPayBottom)
                deselectTab(tvTabMembership, vTabMembershipBottom)
            }
        }
    }

    private fun setupCardRecyclerView() {
        cardAdapter = CardAdapter { selectedCard ->
            if (selectedCard.id != 4) {
                handleCardSelection(selectedCard)
            }
        }

        val defaultSelectedCard = cardList.find { it.id == defaultSelectedCardId }
        defaultSelectedCard?.let { cardAdapter.setSelectedCard(it) }

        with(binding.rvCardList) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = cardAdapter
        }
        cardAdapter.submitList(cardList)
    }

    private fun setupTitleColor() {
        with(binding) {
            val spannableRecommendTitle = SpannableString(tvRecommendTitle.text)
            spannableRecommendTitle.setSpan(
                ForegroundColorSpan(Color.parseColor("#3BE084")),
                0,
                5,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvRecommendTitle.text = spannableRecommendTitle

            val spannableEvent = SpannableString(tvEvent.text)
            spannableEvent.setSpan(
                ForegroundColorSpan(Color.parseColor("#FFFFFF")),
                0,
                3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvEvent.text = spannableEvent
        }
    }

    private fun setupEventRecyclerView() {
        eventAdapter = EventAdapter(eventList)

        with(binding.rvEventList) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventAdapter
        }
    }

    private fun setupRecentPayment() {
        with(binding) {
            val tvCardBalance = tvCardBalance
            tvCardBalance.text = formatBalance(mockApiResponse.data.userPoint)

            ivRecentPlace.load(mockApiResponse.data.onsitePayment.logoImgUrl.toInt()) {
                crossfade(true)
                error(R.drawable.img_recent_blank)
            }

            val formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd a hh:mm:ss")
            val parsedDate = LocalDateTime.parse(mockApiResponse.data.onsitePayment.paymentDate, formatter)
            val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MM.dd"))

            tvRecentPlace.text =
                "${mockApiResponse.data.onsitePayment.name} ${mockApiResponse.data.onsitePayment.place}"
            tvRecentPrice.text =
                "-${formatBalance(mockApiResponse.data.onsitePayment.amount)} " + getString(R.string.tv_recent_price_unit)
            tvRecentDate.text = formattedDate
        }
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