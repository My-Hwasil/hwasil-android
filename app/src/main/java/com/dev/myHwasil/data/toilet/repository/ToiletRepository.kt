package com.dev.myHwasil.data.toilet.repository

import com.dev.myHwasil.data.toilet.dto.FindToiletByIdInput
import com.dev.myHwasil.domain.toilet.Toilet
import kotlinx.coroutines.flow.Flow

interface ToiletRepository {
    suspend fun findById(input: FindToiletByIdInput): Flow<Toilet>
    suspend fun findNearByAddress(address: String): Flow<List<Toilet>>

}