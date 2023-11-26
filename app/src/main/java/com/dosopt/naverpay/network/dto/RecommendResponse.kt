package com.dosopt.naverpay.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendResponse(
    @SerialName("brand_list")
    val brandList: List<BrandListDto> = listOf()
) {
    @Serializable
    data class BrandListDto(
        @SerialName("discount_content")
        val discountContent: String = "",
        @SerialName("discount_type")
        val discountType: String = "",
        @SerialName("id")
        val id: Long = 0,
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("name")
        val name: String = ""
    )
}