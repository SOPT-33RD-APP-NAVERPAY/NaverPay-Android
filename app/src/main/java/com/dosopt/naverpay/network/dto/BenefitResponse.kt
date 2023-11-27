package com.dosopt.naverpay.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BenefitResponse(
    @SerialName("brand_list")
    val brandList: List<BrandListDto> = listOf(),
    @SerialName("user_name")
    val userName: String = "",
    @SerialName("user_point")
    val userPoint: Int = 0
) {
    @Serializable
    data class BrandListDto(
        @SerialName("discount_content")
        val discountContent: String = "",
        @SerialName("discount_type")
        val discountType: String = "",
        @SerialName("id")
        val id: Long = 0,
        @SerialName("is_brand_like")
        val isBrandLike: Boolean = false,
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("name")
        val name: String = ""
    )
}