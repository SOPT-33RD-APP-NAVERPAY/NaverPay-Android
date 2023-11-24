package com.dosopt.naverpay.ui.main.benefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.FragmentBenefitBinding
import com.dosopt.naverpay.domain.model.benefit.Brand
import com.dosopt.naverpay.ui.main.benefit.adapter.AdmobAdapter
import com.dosopt.naverpay.ui.main.benefit.adapter.CardMenuAdapter
import com.dosopt.naverpay.ui.main.benefit.adapter.ImmediateBrandAdapter
import com.dosopt.naverpay.ui.main.benefit.adapter.PopularBrandAdapter
import com.dosopt.naverpay.ui.main.benefit.decorator.CardMenuItemDecoration
import com.dosopt.naverpay.ui.main.benefit.decorator.DividerItemDecoration
import com.dosopt.naverpay.util.fragment.toast

class BenefitFragment : Fragment() {
    private var _binding: FragmentBenefitBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val benefitViewModel: BenefitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBenefitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupViewPager()
        observeViewModelData()
        initializeMenuButtons()
    }

    private fun setupRecyclerViews() {
        setupRecyclerView(binding.rvBenefitPointMenu,
            LinearLayoutManager.HORIZONTAL,
            CardMenuAdapter(),
            CardMenuItemDecoration(requireContext())
        )
        setupRecyclerView(
            binding.rvBenefitPopularBrand,
            LinearLayoutManager.VERTICAL,
            PopularBrandAdapter(::onLikeButtonClicked)
        )
        setupRecyclerView(
            binding.rvBenefitImmediateBrand,
            LinearLayoutManager.VERTICAL,
            ImmediateBrandAdapter(),
            DividerItemDecoration(requireContext())
        )

        binding.rvBenefitImmediateBrand.isEnabled = false
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        orientation: Int,
        adapter: RecyclerView.Adapter<*>,
        itemDecoration: RecyclerView.ItemDecoration? = null
    ) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, orientation, false)
            this.adapter = adapter
            itemDecoration?.let { addItemDecoration(it) }
        }
    }

    private fun setupViewPager() {
        val admobAdapter = AdmobAdapter()
        binding.vpBenefitAdmob.adapter = admobAdapter
    }

    private fun observeViewModelData() {
        benefitViewModel.cardImages.observe(viewLifecycleOwner) { urls ->
            (binding.rvBenefitPointMenu.adapter as CardMenuAdapter).submitList(urls)
        }

        benefitViewModel.mockPopularBrands.observe(viewLifecycleOwner) { brands ->
            (binding.rvBenefitPopularBrand.adapter as PopularBrandAdapter).submitList(brands)
        }

        benefitViewModel.mockImmediateBrands.observe(viewLifecycleOwner) { brands ->
            (binding.rvBenefitImmediateBrand.adapter as ImmediateBrandAdapter).submitList(brands)
        }

        benefitViewModel.admobImages.observe(viewLifecycleOwner) { images ->
            (binding.vpBenefitAdmob.adapter as AdmobAdapter).submitList(images)
        }

        benefitViewModel.mockUserInfo.observe(viewLifecycleOwner) { userInfo ->
            binding.tvBenefitUserName.text = getString(R.string.benefit_name, userInfo.user_name)
            val formattedPoints = String.format("%,d", userInfo.user_point.toInt())
            binding.tvBenefitUserPoint.text = getString(R.string.benefit_user_point, formattedPoints)
        }
    }

    private fun initializeMenuButtons() {
        val menuButtons = mapOf(
            binding.ibBenefitPointMenu1 to Pair(R.drawable.ic_point_menu_1_on, R.drawable.ic_point_menu_1_off),
            binding.ibBenefitPointMenu2 to Pair(R.drawable.ic_point_menu_2_on, R.drawable.ic_point_menu_2_off),
            binding.ibBenefitPointMenu3 to Pair(R.drawable.ic_point_menu_3_on, R.drawable.ic_point_menu_3_off),
            binding.ibBenefitPointMenu4 to Pair(R.drawable.ic_point_menu_4_on, R.drawable.ic_point_menu_4_off),
            binding.ibBenefitPointMenu5 to Pair(R.drawable.ic_point_menu_5_on, R.drawable.ic_point_menu_5_off
            ),
        )
        menuButtons.forEach { (button, states) ->
            button.setOnClickListener { updateMenuButtons(button, menuButtons) }
        }
        binding.ibBenefitPointMenu1.setBackgroundResource(R.drawable.ic_point_menu_1_on)
    }

    private fun updateMenuButtons(
        selectedButton: ImageButton,
        menuButtons: Map<ImageButton, Pair<Int, Int>>
    ) {
        menuButtons.forEach { (button, states) ->
            button.setBackgroundResource(if (button == selectedButton) states.first else states.second)
        }
    }

    private fun onLikeButtonClicked(brand: Brand) {
        toast("${brand.id}번이 클릭됨")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
