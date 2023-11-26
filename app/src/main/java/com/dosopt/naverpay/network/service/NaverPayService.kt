package com.dosopt.naverpay.network.service

import com.dosopt.naverpay.network.dto.BaseResponse
import com.dosopt.naverpay.network.dto.BenefitResponse
import com.dosopt.naverpay.network.dto.HomeResponse
import com.dosopt.naverpay.network.dto.LikeResponse
import com.dosopt.naverpay.network.dto.PlaceResponse
import com.dosopt.naverpay.network.dto.PointResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface NaverPayService {

    @Headers("Content-Type: application/json", "X-Auth-id: 1")
    @GET("")
    suspend fun homeLogin(): BaseResponse<HomeResponse>

    @Headers("Content-Type: application/json", "X-Auth-id: 1")
    @GET("/place")
    suspend fun getPlacePayment(): BaseResponse<PlaceResponse>

    @Headers("Content-Type: application/json", "X-Auth-id: 1")
    @GET("/point")
    suspend fun getPointInfo(): BaseResponse<PointResponse>

    @Headers("Content-Type: application/json", "X-Auth-id: 1")
    @GET("/benefit")
    suspend fun getBenefitInfo(): BaseResponse<BenefitResponse>

    @Headers("Content-Type: application/json", "X-Auth-id: 1")
    @POST("/benefit/like/{brandId}")
    suspend fun sendLike(
        @Path("brandId") brandId: String,
    ): BaseResponse<LikeResponse>

    @Headers("Content-Type: application/json", "X-Auth-id: 1")
    @DELETE("/benefit/like/{brandId}")
    suspend fun deleteLike(
        @Path("brandId") brandId: String,
    ): BaseResponse<LikeResponse>

    @Headers("Content-Type: application/json")
    @GET("/benefit/recommend")
    suspend fun getRecommend(): BaseResponse<BenefitResponse>
}