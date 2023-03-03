package com.dev.myHwasil.di

import com.dev.myHwasil.data.data_source.RemoteDataSource
import com.dev.myHwasil.data.toilet.api.ToiletApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private val dataSource
        get() = RemoteDataSource.client

    @Singleton
    @Provides
    fun provideToiletApi() = dataSource.create<ToiletApi>()
}