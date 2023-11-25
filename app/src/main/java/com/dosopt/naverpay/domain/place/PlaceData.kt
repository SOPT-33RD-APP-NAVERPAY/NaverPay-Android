package com.dosopt.naverpay.domain.place


data class User(
    val user_name: String = ""
)

data class NearbyplaceList(
    val id: Long,
    val logo_img_url: String,
    val name: String,
    val distance: Int
) {
    val distanceValue: String = "$distance m"
}

data class BrandList(
    val id: Long,
    val logo_img_url: String
)

data class OnsitepaymentList(
    val id: Long,
    val logo_img_url: String,
    val name: String
)



