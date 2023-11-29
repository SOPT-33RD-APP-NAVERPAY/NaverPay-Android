package com.dosopt.naverpay.ui.main.benefit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dosopt.naverpay.R
import com.dosopt.naverpay.domain.model.benefit.BenefitBrand
import com.dosopt.naverpay.domain.model.benefit.BenefitUser
import com.dosopt.naverpay.network.ServicePool
import kotlinx.coroutines.launch

class BenefitViewModel : ViewModel() {

    private var _popularBrands: MutableLiveData<List<BenefitBrand>> = MutableLiveData()
    val popularBrands: MutableLiveData<List<BenefitBrand>>
        get() = _popularBrands

    private var _userInfo: MutableLiveData<BenefitUser> = MutableLiveData()
    val userInfo: MutableLiveData<BenefitUser>
        get() = _userInfo

    private var _immediateBrands: MutableLiveData<List<BenefitBrand>> = MutableLiveData()
    val immediateBrands: MutableLiveData<List<BenefitBrand>>
        get() = _immediateBrands

    private var _postLikeSuccess = MutableLiveData<Boolean>()
    val postLikeSuccess: MutableLiveData<Boolean>
        get() = _postLikeSuccess

    private var _deleteLikeSuccess = MutableLiveData<Boolean>()
    val deleteLikeSuccess: MutableLiveData<Boolean>
        get() = _deleteLikeSuccess


    fun getBenefitInfo() {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.getBenefitInfo()
            }.onSuccess { response ->
                _popularBrands.value = response.data?.toBenefitBrandList()
                _userInfo.value = response.data?.toBenefitUser()
            }.onFailure {
                Log.e("BenefitViewModel", "getBenefitInfo() error: ${it.message}")
            }
        }
    }

    fun getRecommend() {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.getRecommend()
            }.onSuccess { response ->
                _immediateBrands.value = response.data?.toBenefitBrandList()
            }.onFailure {
                Log.e("BenefitViewModel", "getRecommend() error: ${it.message}")
            }
        }
    }

    fun postBrandLike(brandId: Long) {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.postBrandLike(brandId)
            }.onSuccess {
                postLikeSuccess.value = true
            }.onFailure {
                Log.e("BenefitViewModel", "getRecommend() error: ${it.message}")
                postLikeSuccess.value = false
            }
        }

    }

    fun deleteBrandLike(brandId: Long) {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.deleteBrandLike(brandId)
            }.onSuccess {
                deleteLikeSuccess.value = true
            }.onFailure {
                Log.e("BenefitViewModel", "getRecommend() error: ${it.message}")
                deleteLikeSuccess.value = false
            }
        }
    }

    fun toggleBrandLike(brandId: Long) {
        _popularBrands.value = _popularBrands.value?.map { brand ->
            if (brand.id == brandId) {
                brand.copy(is_brand_like = !brand.is_brand_like)
            } else {
                brand
            }
        }
    }

    // local data

    val cardImages = MutableLiveData(
        listOf(
            R.drawable.img_benefit_btn_card_1,
            R.drawable.img_benefit_btn_card_2,
            R.drawable.img_benefit_btn_card_3,
            R.drawable.img_benefit_btn_card_3
        )
    )

    val admobImages = MutableLiveData(
        listOf(
            R.drawable.img_ad_pica,
            R.drawable.img_ad_tj,
            R.drawable.img_ad_paris,
        )
    )
}