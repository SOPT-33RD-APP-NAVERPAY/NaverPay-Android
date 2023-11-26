package com.dosopt.naverpay.network.dto

import kotlinx.serialization.SerialName

class RecommendRequest(
    @SerialName("Content-Type")
    val contentType: String
)