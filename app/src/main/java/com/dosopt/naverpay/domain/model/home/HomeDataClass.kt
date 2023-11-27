package com.dosopt.naverpay.domain.model.home

data class CardInfo(
    val id: Int,
    val img: Int
)

data class EventInfo(
    val id: Int,
    val logo: Int,
    val detail: String,
    val percentage: String,
    val background: String
)

data class BrandInfo(
    val id: Long,
    val name: String,
    val place: String,
    val logoImgUrl: String,
    val discountContent: String
)