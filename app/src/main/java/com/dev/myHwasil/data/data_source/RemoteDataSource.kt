package com.dev.myHwasil.data.data_source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    val client: Retrofit = Retrofit.Builder()
        .baseUrl("http://43.201.149.231:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}