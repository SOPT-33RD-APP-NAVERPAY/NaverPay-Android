package com.dosopt.naverpay.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dosopt.naverpay.R
import com.dosopt.naverpay.domain.model.home.CardInfo
import com.dosopt.naverpay.domain.model.home.EventInfo
import com.dosopt.naverpay.network.ServicePool
import com.dosopt.naverpay.network.dto.HomeResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val selectedCardList = mutableListOf<CardInfo>()
    val defaultSelectedCardId = 1

    private val _userDto = MutableLiveData<HomeResponse>()
    val userDto: LiveData<HomeResponse> get() = _userDto

    private val _brandListDto = MutableLiveData<List<HomeResponse.BrandListDto>>()
    val brandListDto: LiveData<List<HomeResponse.BrandListDto>> get() = _brandListDto

    private val _onsitePayment = MutableLiveData<HomeResponse.OnsitePaymentDto>()
    val onsitePayment: LiveData<HomeResponse.OnsitePaymentDto> get() = _onsitePayment

    fun getHomeInfo() {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.homeLogin()
            }.onSuccess { response ->
                _userDto.value = response.data ?: HomeResponse()
                _brandListDto.value = response.data?.brandList ?: listOf()
                _onsitePayment.value =
                    response.data?.onsitePayment ?: HomeResponse.OnsitePaymentDto()
            }.onFailure {
                Log.e("HomeNetworkTest", "error:$it")
            }
        }
    }

    val cardList = listOf(
        CardInfo(1, R.drawable.img_card_1),
        CardInfo(2, R.drawable.img_card_2),
        CardInfo(3, R.drawable.img_card_3),
        CardInfo(4, R.drawable.img_card_add)
    )

    val eventList = listOf(
        EventInfo(
            id = 1,
            logo = R.drawable.img_event_1,
            detail = "매일매일 더블혜택",
            percentage = "최대 10%",
            background = "#00472E"
        ),
        EventInfo(
            id = 2,
            logo = R.drawable.img_event_2,
            detail = "매일매일 더블혜택",
            percentage = "최대 20%",
            background = "#6D2993"
        ),
        EventInfo(
            id = 3,
            logo = R.drawable.img_event_3,
            detail = "매일매일 더블혜택",
            percentage = "최대 30%",
            background = "#003985"
        ),
    )
}