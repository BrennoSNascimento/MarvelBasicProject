package com.example.projeto.projectmarvel.base

import com.example.projeto.projectmarvel.data.homeApi.HomeApi
import com.example.projeto.projectmarvel.domain.repository.HomeRepository
import com.example.projeto.projectmarvel.domain.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideHomeRepository(homeApi: HomeApi) : HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }

}