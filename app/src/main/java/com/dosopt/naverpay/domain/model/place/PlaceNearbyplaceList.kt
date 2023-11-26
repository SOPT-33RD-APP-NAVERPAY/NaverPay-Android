package com.dosopt.naverpay.domain.model.place

data class NearbyplaceList(
    val id: Long,
    val logo_img_url: String,
    val name: String,
    val distance: Int
) {
    val distanceValue: String = "$distance m"
}



