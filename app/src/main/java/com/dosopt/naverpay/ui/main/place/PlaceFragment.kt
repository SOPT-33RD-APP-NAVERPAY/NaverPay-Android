package com.dosopt.naverpay.ui.main.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dosopt.naverpay.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {
    private var _binding: FragmentPlaceBinding? = null
    private val binding: FragmentPlaceBinding
        get() = requireNotNull(_binding) { "_binding is  null" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}