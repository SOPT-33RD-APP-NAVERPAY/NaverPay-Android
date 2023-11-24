package com.dosopt.naverpay.domain.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
            logoImgUrl = "",
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