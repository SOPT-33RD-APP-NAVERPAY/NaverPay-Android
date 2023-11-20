package com.dosopt.naverpay.ui.main.benefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dosopt.naverpay.databinding.FragmentBenefitBinding

class BenefitFragment : Fragment() {
    private var _binding: FragmentBenefitBinding? = null
    private val binding: FragmentBenefitBinding
    get() = requireNotNull(_binding) { "_binding is  null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBenefitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}