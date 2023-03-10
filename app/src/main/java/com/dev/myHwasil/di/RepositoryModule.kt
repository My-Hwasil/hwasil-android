package com.dev.myHwasil.di

import com.dev.myHwasil.data.toilet.api.ToiletApi
import com.dev.myHwasil.data.toilet.repository.ToiletRepository
import com.dev.myHwasil.data.toilet.repository.ToiletRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideToiletRepository(service: ToiletApi): ToiletRepository =
        ToiletRepositoryImpl(service)
}