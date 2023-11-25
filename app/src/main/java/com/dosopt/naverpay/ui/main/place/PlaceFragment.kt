package com.dosopt.naverpay.ui.main.place

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentPlaceBinding
import com.dosopt.naverpay.ui.main.benefit.BenefitFragment
import com.dosopt.naverpay.ui.main.home.HomeFragment

class PlaceFragment : Fragment() {
    private var _binding: FragmentPlaceBinding? = null
    private val binding: FragmentPlaceBinding
        get() = requireNotNull(_binding) { "_binding is  null" }
    private val viewModel = PlaceViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDiv()
        setupAdapter()
        setupTabs()
        clickXbtn()
        clickMapbtn()
    }

    //어댑터 붙이기
    private fun setupAdapter() {
        val placeNearbyAdapter = PlaceNearbyAdapter()
        val placeRecommendAdapter = PlaceRecommendAdapter()
        val placePaymentAdapter = PlacePaymentAdapter()

        with(binding) {
            rvPlace.adapter = placeNearbyAdapter
            rvRecommend.adapter = placeRecommendAdapter
            rvPlacePayment.adapter = placePaymentAdapter
        }

        placeNearbyAdapter.submitList(viewModel.mockNearbyplaceList.value.orEmpty())
        placeRecommendAdapter.submitList(viewModel.mockBrandList.value.orEmpty())
        placePaymentAdapter.submitList(viewModel.mockOnsitepaymentList.value.orEmpty())
    }

    //탭 선택시
    private fun selectTab(textView: TextView, bottomView: View) {
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.bg_black))
        bottomView.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.main_lightgreen
            )
        )
    }

    //탭 미선택시
    private fun deselectTab(textView: TextView, bottomView: View) {
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayscale_gray7))
        bottomView.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )
    }

    //탭 눌렀을 때
    private fun setupTabs() {
        with(binding) {
            tvDome.setOnClickListener {
                selectTab(tvDome, viRecDome)
                deselectTab(tvInter, viRecInter)
            }

            tvInter.setOnClickListener {
                selectTab(tvInter, viRecInter)
                deselectTab(tvDome, viRecDome)
            }
        }
    }

    private fun setDiv() {
        with(binding) {
            rvPlace.addItemDecoration(VerticalItemDecorator(12))
            rvPlace.addItemDecoration(HorizontalItemDecorator(8))
            rvRecommend.addItemDecoration(VerticalItemDecorator(12))
            rvRecommend.addItemDecoration(HorizontalItemDecorator(6))
            rvPlacePayment.addItemDecoration(VerticalItemDecorator(12))
            rvPlacePayment.addItemDecoration(HorizontalItemDecorator(8))
        }
    }

    class VerticalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {
        @Override
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = divHeight
            outRect.bottom = divHeight
        }
    }

    class HorizontalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {

        @Override
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = divHeight
            outRect.right = divHeight
        }
    }

    private fun clickXbtn() {
        binding.ivBtnX.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.iv_btn_x, HomeFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun clickMapbtn() {
        binding.btnMapBottom.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.btn_map_bottom, BenefitFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}