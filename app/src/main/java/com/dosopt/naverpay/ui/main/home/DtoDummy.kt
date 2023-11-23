package com.dosopt.naverpay.ui.main.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto(
    @SerialName("user_point")
    val user_point: Int,
    @SerialName("onsite_payment")
    val onsite_payment: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("place")
    val place: String,
    @SerialName("logo_img_url")
    val logo_img_url: String,
    @SerialName("amount")
    val amount: Int,
    @SerialName("payment_date")
    val payment_date: String,
)

@Serializable
data class ResponseRecommendInfoDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("place")
    val place: String,
    @SerialName("logo_img_url")
    val logo_img_url: String,
    @SerialName("discount_content")
    val payment_date: String,
)