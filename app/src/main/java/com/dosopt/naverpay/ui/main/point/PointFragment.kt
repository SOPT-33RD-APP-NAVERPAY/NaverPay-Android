package com.dosopt.naverpay.ui.main.point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentPointBinding
import com.dosopt.naverpay.ui.main.point.adpater.PointAdapter

class PointFragment : Fragment() {
    private var _binding: FragmentPointBinding? = null
    private val binding: FragmentPointBinding
        get() = requireNotNull(_binding) { "_binding is  null" }

    private lateinit var pointAdapter: PointAdapter
    private val viewModel by viewModels<PointViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pointAdapter = PointAdapter()
        viewModel.getPointInfo()
        setupUserPointInfo()
    }

    private fun setupUserPointInfo() {
        viewModel.userDto.observe(this) { userDto ->
            with(binding) {
                tvPointUserPoint.text = getString(R.string.tv_point_user_point, userDto.userPoint)
                tvPointGraphTotalPoint.text =
                    getString(R.string.tv_point_user_point, userDto.userPoint)
                setupPointRecyclerView()
            }
        }
    }

    private fun setupPointRecyclerView() {
        with(binding.rvPointReward) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = pointAdapter
        }

        viewModel.pointRewardListDto.observe(viewLifecycleOwner) { pointRewardListDto ->
            pointAdapter.submitList(pointRewardListDto)
        }
    }
}
