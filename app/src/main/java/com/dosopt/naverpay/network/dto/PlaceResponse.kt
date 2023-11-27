package com.dosopt.naverpay.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    @SerialName("brand_list")
    val brandList: List<BrandListDto> = listOf(),
    @SerialName("nearbyplace_list")
    val nearbyplaceList: List<NearbyplaceListDto> = listOf(),
    @SerialName("onsitepayment_list")
    val onsitepaymentList: List<OnsitepaymentListDto> = listOf(),
    @SerialName("user_name")
    val userName: String = ""
) {
    @Serializable
    data class OnsitepaymentListDto(
        @SerialName("id")
        val id: Long = 0,
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("name")
        val name: String = ""
    )

    @Serializable
    data class BrandListDto(
        @SerialName("id")
        val id: Long = 0,
        @SerialName("logo_img_url")
        val logoImgUrl: String = ""
    )

    @Serializable
    data class NearbyplaceListDto(
        @SerialName("distance")
        val distance: Int = 0,
        @SerialName("id")
        val id: Long = 0,
        @SerialName("logo_img_url")
        val logoImgUrl: String = "",
        @SerialName("name")
        val name: String = ""
    )
}