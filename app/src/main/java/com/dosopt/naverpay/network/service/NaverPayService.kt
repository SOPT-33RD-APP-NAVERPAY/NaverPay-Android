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

    // 홈 화면
    @Headers("X-Auth-id: 1")
    @GET("/")
    suspend fun homeLogin(): BaseResponse<HomeResponse>

    // 현장결제_장소 조회
    @Headers("X-Auth-id: 1")
    @GET("/place")
    suspend fun getPlacePayment(): BaseResponse<PlaceResponse>

    // 포인트_포인트 내역 조회
    @Headers("X-Auth-id: 1")
    @GET("/point")
    suspend fun getPointInfo(): BaseResponse<PointResponse>

    // 혜택_내역 조회
    @Headers("X-Auth-id: 1")
    @GET("/benefit")
    suspend fun getBenefitInfo(): BaseResponse<BenefitResponse>

    // 혜택_찜하기 등록
    @Headers("X-Auth-id: 1")
    @POST("/benefit/like/{brandId}")
    suspend fun sendLike(
        @Path("brandId") brandId: String,
    ): BaseResponse<LikeResponse>

    // 혜택_찜하기 취소
    @Headers("X-Auth-id: 1")
    @DELETE("/benefit/like/{brandId}")
    suspend fun deleteLike(
        @Path("brandId") brandId: String,
    ): BaseResponse<LikeResponse>

    // 혜택_포인트 추천 내역 조회
    @GET("/benefit/recommend")
    suspend fun getRecommend(): BaseResponse<BenefitResponse>

}