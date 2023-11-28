package com.dosopt.naverpay.ui.main.place

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dosopt.naverpay.network.ServicePool
import com.dosopt.naverpay.network.dto.PlaceResponse
import kotlinx.coroutines.launch

class PlaceViewModel : ViewModel() {

    private val _placeResult: MutableLiveData<PlaceResponse> = MutableLiveData()
    val placeResult: LiveData<PlaceResponse> = _placeResult

    private val _placeSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val placeSuccess: LiveData<Boolean> = _placeSuccess

    private val _placeResponse = MutableLiveData<PlaceResponse>()
    val placeResponse: LiveData<PlaceResponse> = _placeResponse

    private val _nearbyplaceList = MutableLiveData<List<PlaceResponse.NearbyplaceListDto>>()
    val nearbyplaceList: LiveData<List<PlaceResponse.NearbyplaceListDto>> = _nearbyplaceList

    private val _brandList = MutableLiveData<List<PlaceResponse.BrandListDto>>()
    val brandList: LiveData<List<PlaceResponse.BrandListDto>> = _brandList

    private val _onsitepaymentList = MutableLiveData<List<PlaceResponse.OnsitepaymentListDto>>()
    val onsitepaymentList: LiveData<List<PlaceResponse.OnsitepaymentListDto>> = _onsitepaymentList

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun getPlacePayment() {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.getPlacePayment()
            }.onSuccess { response -> //BaseResponse<PlaceResponse>
                _userName.value = response.data?.userName
                _nearbyplaceList.value = response.data?.nearbyplaceList
                _brandList.value = response.data?.brandList
                _onsitepaymentList.value = response.data?.onsitepaymentList

            }.onFailure {
                Log.e("NetworkTest1", "error:$it")
            }
        }
    }
}

