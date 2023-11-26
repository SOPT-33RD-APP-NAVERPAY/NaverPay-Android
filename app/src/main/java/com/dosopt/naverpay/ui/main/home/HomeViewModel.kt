package com.dosopt.naverpay.ui.main.home

import com.dosopt.naverpay.R
import com.dosopt.naverpay.domain.home.ApiResponse
import com.dosopt.naverpay.domain.home.Brand
import com.dosopt.naverpay.domain.home.CardInfo
import com.dosopt.naverpay.domain.home.EventInfo
import com.dosopt.naverpay.domain.home.HomeData
import com.dosopt.naverpay.domain.home.OnsitePayment

class HomeViewModel {
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