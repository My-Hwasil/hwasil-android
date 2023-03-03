package com.dev.myHwasil.data.toilet.repository


import com.dev.myHwasil.data.toilet.api.ToiletApi
import com.dev.myHwasil.data.toilet.dto.FindToiletByIdInput
import com.dev.myHwasil.domain.toilet.Toilet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ToiletRepositoryImpl(
    private val toiletApi: ToiletApi
) : ToiletRepository {
    override suspend fun findById(input: FindToiletByIdInput): Flow<Toilet> {
        return flow {
//            val res = toiletApi.findById(input)

            emit(
                Toilet(
                    id = 11147,
                    title = "영등포여고앞",
                    latitude = 37.516168,
                    longitude = 126.915405
                )
            )
        }
    }

    override suspend fun findNearByAddress(address: String): Flow<List<Toilet>> {
        // val res = toiletApi.findNearByAddress(address)

        return flow {
            val res = toiletApi.findNearByAddress(address)
            val result = res.map {
                Toilet(
                    id = it.toiletNo,
                    title = it.toiletNm,
                    latitude = it.latitude.toDouble(),
                    longitude = it.longitude.toDouble(),
                )
            }
            emit(result)
        }
    }
}