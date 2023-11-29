package com.dosopt.naverpay.ui.main.point

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentPointBinding
import com.dosopt.naverpay.ui.main.point.adpater.PointAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter

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
            setupPieChart(
                userDto.paymentMethodPoint, userDto.reviewPoint,
                userDto.membershipPoint, userDto.basicPoint
            )
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

    private fun setupPieChart(
        paymentMethodPoint: Int,
        reviewPoint: Int,
        membershipPoint: Int,
        basicPoint: Int
    ) {
        val pieDataSet =
            createPieDataSet(paymentMethodPoint, reviewPoint, membershipPoint, basicPoint)
        configurePieChartAppearance()
        configurePieChartData(pieDataSet)
    }

    private fun createPieDataSet(
        paymentMethodPoint: Int,
        reviewPoint: Int,
        membershipPoint: Int,
        basicPoint: Int
    ): PieDataSet {
        val pieEntries = listOf(
            PieEntry(paymentMethodPoint.toFloat(), getString(R.string.point_graph_payment)),
            PieEntry(reviewPoint.toFloat(), getString(R.string.point_graph_review)),
            PieEntry(membershipPoint.toFloat(), getString(R.string.point_graph_membership)),
            PieEntry(basicPoint.toFloat(), getString(R.string.point_graph_base))
        )
        val pieColors = listOf(
            ContextCompat.getColor(requireContext(), R.color.graph_payment),
            ContextCompat.getColor(requireContext(), R.color.graph_review),
            ContextCompat.getColor(requireContext(), R.color.graph_membership),
            ContextCompat.getColor(requireContext(), R.color.graph_main),
        )

        return PieDataSet(pieEntries, "").apply {
            colors = pieColors
            setDrawValues(true)
        }
    }

    private fun configurePieChartAppearance() {
        with(binding.pieChartPointGraph) {
            setTouchEnabled(false)
            animateY(1200, Easing.EaseInOutCubic)
            setTransparentCircleAlpha(0)
            holeRadius = 45f
        }

        configurePieChartCenterText()
        configurePieChartLabels()
    }

    private fun configurePieChartCenterText() {
        val regularTypeface =
            Typeface.createFromAsset(requireContext().assets, "fonts/roboto_regular.ttf")

        with(binding.pieChartPointGraph) {
            centerText = context.getString(R.string.point_graph_center_text)
            setCenterTextTypeface(regularTypeface)
            setCenterTextSize(10f)
            setCenterTextColor(ContextCompat.getColor(requireContext(), R.color.grayscale_gray6))
        }
    }

    private fun configurePieChartLabels() {
        val boldTypeface =
            Typeface.createFromAsset(requireContext().assets, "fonts/roboto_bold.ttf")

        with(binding.pieChartPointGraph) {
            setEntryLabelTypeface(boldTypeface)
            setEntryLabelTextSize(11f)
            setEntryLabelColor(ContextCompat.getColor(requireContext(), R.color.white))
            description.isEnabled = false
            legend.isEnabled = false
        }
    }

    private fun configurePieChartData(dataSet: PieDataSet) {
        val mediumTypeface =
            Typeface.createFromAsset(requireContext().assets, "fonts/roboto_medium.ttf")

        with(binding.pieChartPointGraph) {
            data = PieData(dataSet).apply {
                setValueTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                setValueTypeface(mediumTypeface)
                setValueTextSize(9f)
                setValueFormatter(MyValueFormatter())
            }
            animate()
        }
    }
}

class MyValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return "${value.toInt()}원"
    }
}
