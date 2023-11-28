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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentPlaceBinding
import com.dosopt.naverpay.network.dto.PlaceResponse
import com.dosopt.naverpay.ui.main.benefit.BenefitFragment
import com.dosopt.naverpay.ui.main.home.HomeFragment

class PlaceFragment : Fragment() {
    private var _binding: FragmentPlaceBinding? = null
    private val binding: FragmentPlaceBinding
        get() = requireNotNull(_binding) { "_binding is  null" }

    private val viewModel by viewModels<PlaceViewModel>()

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
        setupTabs()
        clickXbtn()
        clickMapbtn()

        with(viewModel) {
            getPlacePayment()

            nearbyplaceList.observe(viewLifecycleOwner, Observer { data ->
                setupNearbyAdapter(data)
            })
            brandList.observe(viewLifecycleOwner, Observer { data ->
                setupRecommendAdapter(data)
            })
            onsitepaymentList.observe(viewLifecycleOwner, Observer { data ->
                setupPaymentAdapter(data)
            })

            userName.observe(viewLifecycleOwner, Observer { data ->
                val nearMessage = getString(R.string.tv_near_place, data)
                val recomMessage = getString(R.string.tv_place_recom, data)
                binding.tvPlaceNear.text = nearMessage
                binding.tvPlaceRecom.text = recomMessage
            })
        }
    }

    //어댑터 붙이기
    private fun setupNearbyAdapter(nearbyplaceList: List<PlaceResponse.NearbyplaceListDto>?) {
        val placeNearbyAdapter = PlaceNearbyAdapter()
        binding.rvPlace.adapter = placeNearbyAdapter
        placeNearbyAdapter.submitList(nearbyplaceList)
    }

    private fun setupRecommendAdapter(brandList: List<PlaceResponse.BrandListDto>?) {
        val placeRecommendAdapter = PlaceRecommendAdapter()
        binding.rvPlaceRecommend.adapter = placeRecommendAdapter
        placeRecommendAdapter.submitList(brandList)
    }

    private fun setupPaymentAdapter(onsitepaymentList: List<PlaceResponse.OnsitepaymentListDto>?) {
        val placePaymentAdapter = PlacePaymentAdapter()
        binding.rvPlacePayment.adapter = placePaymentAdapter
        placePaymentAdapter.submitList(onsitepaymentList)
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
            tvPlaceDome.setOnClickListener {
                selectTab(tvPlaceDome, viPlaceRecDome)
                deselectTab(tvPlaceInter, viPlaceRecInter)
            }

            tvPlaceInter.setOnClickListener {
                selectTab(tvPlaceInter, viPlaceRecInter)
                deselectTab(tvPlaceDome, viPlaceRecDome)
            }
        }
    }

    private fun setDiv() {
        with(binding) {
            rvPlace.addItemDecoration(VerticalItemDecorator(12))
            rvPlace.addItemDecoration(HorizontalItemDecorator(8))
            rvPlaceRecommend.addItemDecoration(VerticalItemDecorator(2))
            rvPlaceRecommend.addItemDecoration(HorizontalItemDecorator(6))
            rvPlacePayment.addItemDecoration(VerticalItemDecorator(2))
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
        binding.ivPlaceBtnX.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.iv_place_btn_x, HomeFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun clickMapbtn() {
        binding.btnPlaceMapBottom.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.btn_place_map_bottom, BenefitFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}