package com.example.sportapp.module

import com.example.data.network.SportApiService
import com.example.data.repository.SportRepositoryImpl
import com.example.domain.repository.SportRepository
import com.example.domain.usecase.SportUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MainModule {

    @Provides
    fun provideSportApiService(retrofit: Retrofit):SportApiService{
        return retrofit.create(SportApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: SportApiService):SportRepository{
        return SportRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providerUseCase(repository: SportRepository):SportUseCase{
        return SportUseCase(repository)
    }


}