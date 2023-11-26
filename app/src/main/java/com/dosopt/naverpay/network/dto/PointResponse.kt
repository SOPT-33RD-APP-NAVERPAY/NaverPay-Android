package com.dosopt.naverpay.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointResponse(
    @SerialName("basic_point")
    val basicPoint: Int = 0,
    @SerialName("brand_list")
    val brandList: List<BrandListDto> = listOf(),
    @SerialName("membership_point")
    val membershipPoint: Int = 0,
    @SerialName("payment_method_point")
    val paymentMethodPoint: Int = 0,
    @SerialName("review_point")
    val reviewPoint: Int = 0,
    @SerialName("user_point")
    val userPoint: Int = 0
) {
    @Serializable
    data class BrandListDto(
        @SerialName("id")
        val id: Long = 0,
        @SerialName("name")
        val name: String = "",
        @SerialName("payment_date")
        val paymentDate: String = "",
        @SerialName("saving")
        val saving: Int = 0
    )
}