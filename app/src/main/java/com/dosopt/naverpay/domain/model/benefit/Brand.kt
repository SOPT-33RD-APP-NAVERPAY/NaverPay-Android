package com.dosopt.naverpay.domain.model.benefit

data class Brand(
    val id: Long,
    val name: String,
    val logo_img_url: String,
    val discount_content: String,
    val discount_type: String,
    val is_brand_like: Boolean
)
