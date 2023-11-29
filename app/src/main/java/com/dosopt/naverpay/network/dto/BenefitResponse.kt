package com.dosopt.naverpay.network.dto


import com.dosopt.naverpay.domain.model.benefit.BenefitBrand
import com.dosopt.naverpay.domain.model.benefit.BenefitUser
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

    fun toBenefitUser() = BenefitUser(
        user_name = userName,
        user_point = userPoint.toLong()
    )

    fun toBenefitBrandList() = brandList.map {
        BenefitBrand(
            id = it.id,
            name = it.name,
            logo_img_url = it.logoImgUrl,
            discount_content = it.discountContent,
            discount_type = it.discountType,
            is_brand_like = it.isBrandLike
        )
    }
}