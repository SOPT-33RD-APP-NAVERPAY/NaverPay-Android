package com.dosopt.naverpay.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class HomeResponse (
    @SerialName("user_point")
    val userPoint: Int = 0,
    @SerialName("onsite_payment")
    val onsitePayment: OnsitePaymentDto,
    @SerialName("brand_list")
    val brandList: List<BrandListDto> = listOf()
){
    @Serializable
    data class BrandListDto(
        @SerialName("id")
        val id: Long = 0,
        @SerialName("name")
        val name: String = "",
        @SerialName("place")
        val place: String = "",
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("discount_content")
        val discountContent: String = "",
    )
    @Serializable
    data class OnsitePaymentDto(
        @SerialName("id")
        val id: Long = 0,
        @SerialName("name")
        val name: String = "",
        @SerialName("place")
        val place: String = "",
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("amount")
        val amount: Int = 0,
        @SerialName("payment_date")
        val paymentData: String = "",
    )
}