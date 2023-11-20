package com.dosopt.naverpay.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dosopt.naverpay.R
import com.dosopt.naverpay.databinding.ActivityMainBinding
import com.dosopt.naverpay.ui.main.benefit.BenefitFragment
import com.dosopt.naverpay.ui.main.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationClickListener()

        if (savedInstanceState == null) {
            setupDefaultFragment()
            setupDefaultBottomNavigation()
        }
    }

    private fun setupBottomNavigationClickListener() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            val fragment = getFragmentForMenuItem(item.itemId)
            if (fragment != null) {
                replaceFragment(fragment)
                true
            } else {
                false
            }
        }
    }

    private fun getFragmentForMenuItem(itemId: Int): Fragment? {
        return when (itemId) {
            R.id.menu_main_home -> HomeFragment()
            R.id.menu_main_benefit -> BenefitFragment()
            else -> null
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun setupDefaultFragment() {
        replaceFragment(HomeFragment())
    }

    private fun setupDefaultBottomNavigation() {
        binding.bnvMain.selectedItemId = R.id.menu_main_home
    }

}