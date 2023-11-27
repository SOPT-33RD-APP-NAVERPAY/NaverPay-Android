package com.dosopt.naverpay.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("brand_list")
    val brandList: List<BrandListDto> = listOf(),
    @SerialName("onsite_payment")
    val onsitePayment: OnsitePayment = OnsitePayment(),
    @SerialName("user_point")
    val userPoint: Int = 0
) {
    @Serializable
    data class BrandListDto(
        @SerialName("discount_content")
        val discountContent: String = "",
        @SerialName("id")
        val id: Long = 0,
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("name")
        val name: String = "",
        @SerialName("place")
        val place: String = ""
    )

    @Serializable
    data class OnsitePayment(
        @SerialName("amount")
        val amount: Int = 0,
        @SerialName("id")
        val id: Long = 0,
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("name")
        val name: String = "",
        @SerialName("payment_date")
        val paymentDate: String = "",
        @SerialName("place")
        val place: String = ""
    )
}