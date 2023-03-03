package com.dev.myHwasil.data.toilet.api

import com.dev.myHwasil.data.toilet.dto.FindToiletByIdInput
import com.dev.myHwasil.data.toilet.dto.ToiletInfoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ToiletApi {
    @GET("findOneToilet")
    suspend fun findById(
        @Body input: FindToiletByIdInput
    ): ToiletInfoDto

    @GET("nearByLnmadr")
    suspend fun findNearByAddress(
        @Query("lnmadr") address: String
    ): List<ToiletInfoDto>
}