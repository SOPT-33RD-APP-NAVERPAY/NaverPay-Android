package com.dosopt.naverpay.ui.main.point

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dosopt.naverpay.network.ServicePool
import com.dosopt.naverpay.network.dto.PointResponse
import kotlinx.coroutines.launch

class PointViewModel : ViewModel() {

    private val _userDto = MutableLiveData<PointResponse>()
    val userDto: LiveData<PointResponse> get() = _userDto

    private val _pointRewardListDto = MutableLiveData<List<PointResponse.BrandListDto>>()
    val pointRewardListDto: LiveData<List<PointResponse.BrandListDto>> get() = _pointRewardListDto

    fun getPointInfo() {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.getPointInfo()
            }.onSuccess { response ->
                val brandList = response.data?.brandList ?: listOf()
                _userDto.value = response.data ?: PointResponse()
                _pointRewardListDto.value = brandList
            }.onFailure {
                Log.e("PointNetworkTest", "error:$it")
            }
        }
    }
}