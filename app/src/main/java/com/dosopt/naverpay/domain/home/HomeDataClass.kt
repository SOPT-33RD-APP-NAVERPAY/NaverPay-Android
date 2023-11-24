package com.dosopt.naverpay.domain.home

import com.dosopt.naverpay.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardInfo(
    @SerialName("id") val id: Int,
    @SerialName("img") val img: Int,
)

@Serializable
data class EventInfo(
    @SerialName("id") val id: Int,
    @SerialName("logo") val logo: Int,
    @SerialName("detail") val detail: String,
    @SerialName("percentage") val percentage: String,
    @SerialName("background") val background: String,
)

@Serializable
data class ApiResponse(
    @SerialName("status")
    val status: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: HomeData,
)

@Serializable
data class HomeData(
    @SerialName("user_point")
    val userPoint: Int,

    @SerialName("onsite_payment")
    val onsitePayment: OnsitePayment,

    @SerialName("brand_list")
    val brandList: List<Brand>,
)

@Serializable
data class OnsitePayment(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("place")
    val place: String,

    @SerialName("logo_img_url")
    val logoImgUrl: String,

    @SerialName("amount")
    val amount: Int,

    @SerialName("payment_date")
    val paymentDate: String,
)

@Serializable
data class Brand(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("place")
    val place: String,

    @SerialName("logo_img_url")
    val logoImgUrl: String,

    @SerialName("discount_content")
    val discountContent: String,
)

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
                logoImgUrl = "",
                discountContent = "네플멤 회원은 CU 최대"
            ),
            Brand(
                id = 2,
                name = "파리바게뜨",
                place = "건대점",
                logoImgUrl = "",
                discountContent = "현장결제 및 포인트 더블혜택"
            ),
            Brand(
                id = 3,
                name = "신라호텔",
                place = " ",
                logoImgUrl = "...",
                discountContent = "30만원 이상 결제시 1만원"
            ),
            Brand(
                id = 4,
                name = "도미노피자",
                place = "건대점",
                logoImgUrl = "",
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
    ),EventInfo(
        id = 2,
        logo = R.drawable.img_event_2,
        detail = "매일매일 더블혜택",
        percentage = "최대 20%",
        background = "#6D2993"
    ),EventInfo(
        id = 3,
        logo = R.drawable.img_event_3,
        detail = "매일매일 더블혜택",
        percentage = "최대 30%",
        background = "#003985"
    ),
)