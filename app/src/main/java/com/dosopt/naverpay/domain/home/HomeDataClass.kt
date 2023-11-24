package com.dosopt.naverpay.domain.home

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