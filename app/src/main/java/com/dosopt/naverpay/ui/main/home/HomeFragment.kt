package com.dosopt.naverpay.ui.main.home

import BrandItemDecoration
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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentHomeBinding
import com.dosopt.naverpay.domain.model.home.CardInfo
import com.dosopt.naverpay.ui.main.home.adapter.BrandAdapter
import com.dosopt.naverpay.ui.main.home.adapter.CardAdapter
import com.dosopt.naverpay.ui.main.home.adapter.EventAdapter
import com.dosopt.naverpay.ui.main.place.PlaceFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "_binding is  null" }

    private lateinit var cardAdapter: CardAdapter
    private lateinit var eventAdapter: EventAdapter
    private lateinit var brandAdapter: BrandAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHomeInfo()

        setupTabs()
        setupUserInfo()
        setupRecentPayment()

        setupCardRecyclerView()
        setupEventRecyclerView()
        setupBrandRecyclerView()

        setupTitleColor()

        moveToRecommend()
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

        val defaultSelectedCard = viewModel.cardList.find { it.id == viewModel.defaultSelectedCardId }
        defaultSelectedCard?.let { cardAdapter.setSelectedCard(it) }

        with(binding.rvCardList) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = cardAdapter
        }
        cardAdapter.submitList(viewModel.cardList)
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

            val spannableEventTitle = SpannableString(tvEventTitle.text)
            spannableEventTitle.setSpan(
                ForegroundColorSpan(Color.parseColor("#FFFFFF")),
                0,
                3,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tvEventTitle.text = spannableEventTitle
        }
    }

    private fun setupEventRecyclerView() {
        eventAdapter = EventAdapter(viewModel.eventList)

        with(binding.rvEventList) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventAdapter
        }
    }

    private fun setupUserInfo() {
        viewModel.userDto.observe(this) { userDto ->
            binding.tvCardBalance.text = formatBalance(userDto.userPoint)
        }
    }

    private fun setupRecentPayment() {
        viewModel.onsitePayment.observe(this) { onsitePayment ->
            with(binding) {
                ivRecentPlace.load(onsitePayment.logoImgUrl) {
                    crossfade(true)
                    error(R.drawable.rectangle_bg_white_radius_6)
                }

                tvRecentPrice.text = getString(R.string.tv_recent_price, onsitePayment.amount)

                val formattedDate = formatPaymentDate(onsitePayment.paymentDate)
                tvRecentDate.text = formattedDate

                tvRecentPlace.text =
                    getString(R.string.tv_recent_place, onsitePayment.name, onsitePayment.place)
            }
        }
    }

    private fun formatPaymentDate(paymentDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val parsedDate = LocalDateTime.parse(paymentDate, formatter)
        return parsedDate.format(DateTimeFormatter.ofPattern("MM.dd"))
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
        if (viewModel.selectedCardList.contains(selectedCard)) {
            viewModel.selectedCardList.remove(selectedCard)
        } else {
            viewModel.selectedCardList.add(selectedCard)
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

    private fun setupBrandRecyclerView() {
        val spacingInPixels = (16 * resources.displayMetrics.density).toInt()
        brandAdapter = BrandAdapter()

        with(binding.rvRecommend) {
            layoutManager = GridLayoutManager(requireContext(), 1)
            addItemDecoration(BrandItemDecoration(requireContext(), 1, spacingInPixels))
            adapter = brandAdapter
        }

        viewModel.brandListDto.observe(viewLifecycleOwner) { brandListDto ->
            brandAdapter.submitList(brandListDto)
        }
    }

    private fun moveToRecommend() {
        binding.rlRecommendAllview.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fcv_main, PlaceFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}