package com.dosopt.naverpay.domain.model.home

data class BrandInfo(
    val id: Long,
    val name: String,
    val place: String,
    val logoImgUrl: String,
    val discountContent: String
)