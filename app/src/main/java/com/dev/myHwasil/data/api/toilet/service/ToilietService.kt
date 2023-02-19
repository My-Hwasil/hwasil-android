package com.dev.myHwasil.data.api.toilet.service

import com.dev.myHwasil.data.api.toilet.dto.ToiletsByRegionResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface ToilietService {

    @GET("")
    suspend fun findToiletsByRegion(
        @Body body: Map<String,String>
    ) : ToiletsByRegionResponse
}