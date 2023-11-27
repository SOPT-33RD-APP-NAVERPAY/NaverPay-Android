package com.dosopt.naverpay.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dosopt.naverpay.R
import com.dosopt.naverpay.domain.model.home.ApiResponse
import com.dosopt.naverpay.domain.model.home.Brand
import com.dosopt.naverpay.domain.model.home.CardInfo
import com.dosopt.naverpay.domain.model.home.EventInfo
import com.dosopt.naverpay.domain.model.home.HomeData
import com.dosopt.naverpay.domain.model.home.OnsitePayment
import com.dosopt.naverpay.network.ServicePool
import com.dosopt.naverpay.network.dto.HomeResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _userDto = MutableLiveData<HomeResponse>()
    val userDto: LiveData<HomeResponse> get() = _userDto

    private val _brandListDto = MutableLiveData<List<HomeResponse.BrandListDto>>()
    val brandListDto: LiveData<List<HomeResponse.BrandListDto>> get() = _brandListDto

    private val _onsitePayment = MutableLiveData<HomeResponse.OnsitePayment>()
    val onsitePayment: LiveData<HomeResponse.OnsitePayment> get() = _onsitePayment

    fun getHomeInfo() {
        viewModelScope.launch {
            runCatching {
                ServicePool.naverPayService.homeLogin()
            }.onSuccess { response ->
                _userDto.value = response.data ?: HomeResponse()
                _brandListDto.value = response.data?.brandList ?: listOf()
                _onsitePayment.value = response.data?.onsitePayment ?: HomeResponse.OnsitePayment()
            }.onFailure {
                Log.e("HomeNetworkTest", "error:$it")
            }
        }
    }

    val mockApiResponse = ApiResponse(
        status = 200,
        message = "홈화면 조회에 성공했습니다.",
        data = HomeData(
            userPoint = 11500,
            onsitePayment = OnsitePayment(
                id = 1,
                name = "GS25",
                place = "건대점",
                logoImgUrl = R.drawable.img_logo_gs25.toString(),
                amount = 25000,
                paymentDate = "2023. 11. 16 오후 12:21:21"
            ),
            brandList = listOf(
                Brand(
                    id = 1,
                    name = "CU",
                    place = "건대점",
                    logoImgUrl = R.drawable.img_brand_1,
                    discountContent = "네플멤 회원은 CU 최대"
                ),
                Brand(
                    id = 2,
                    name = "파리바게뜨",
                    place = "건대점",
                    logoImgUrl = R.drawable.img_brand_2,
                    discountContent = "현장결제 및 포인트 더블혜택"
                ),
                Brand(
                    id = 3,
                    name = "신라호텔",
                    place = " ",
                    logoImgUrl = R.drawable.img_brand_3,
                    discountContent = "30만원 이상 결제시 1만원"
                ),
                Brand(
                    id = 4,
                    name = "도미노피자",
                    place = "건대점",
                    logoImgUrl = R.drawable.img_brand_4,
                    discountContent = "QR결제시 최대 2천원 할인"
                )
            )
        )
    )

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