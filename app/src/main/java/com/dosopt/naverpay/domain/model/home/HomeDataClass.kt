package com.dosopt.naverpay.domain.model.home

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
data class BrandInfo(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("place")
    val place: String,

    @SerialName("logo_img_url")
    val logoImgUrl: String,

    @SerialName("discount_content")
    val discountContent: String,
)