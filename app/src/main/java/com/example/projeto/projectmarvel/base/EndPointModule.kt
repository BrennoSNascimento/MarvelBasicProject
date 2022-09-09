package com.example.projeto.projectmarvel.base

import com.example.projeto.projectmarvel.data.detailEndPoint.DetailEndPoint
import com.example.projeto.projectmarvel.data.homeEndPoint.HomeEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EndPointModule {
    @Singleton
    @Provides
    fun provideHomeEndPoint(retrofit: Retrofit) : HomeEndPoint{
        return retrofit.create(HomeEndPoint::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailEndPoint(retrofit: Retrofit) : DetailEndPoint{
        return retrofit.create(DetailEndPoint::class.java)
    }
}