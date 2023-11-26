package com.dosopt.naverpay.network.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeResponse(
    @SerialName("brand_id")
    val brandId: Long = 0,
    @SerialName("is_brand_like")
    val isBrandLike: Boolean = false
)