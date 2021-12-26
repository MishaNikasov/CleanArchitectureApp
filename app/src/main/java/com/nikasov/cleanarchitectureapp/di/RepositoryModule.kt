package com.nikasov.cleanarchitectureapp.di

import com.nikasov.cleanarchitectureapp.data.local.dao.ScreenshotDao
import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.data.repository.GamesRepositoryImpl
import com.nikasov.cleanarchitectureapp.data.repository.ScreenshotRepositoryImpl
import com.nikasov.cleanarchitectureapp.data.repository.SearchRepositoryImpl
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
import com.nikasov.cleanarchitectureapp.domain.repository.ScreenshotRepository
import com.nikasov.cleanarchitectureapp.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGameRepository(
        networkApi: NetworkApi
    ): GamesRepository {
        return GamesRepositoryImpl(networkApi)
    }

    @Provides
    @Singleton
    fun provideScreenshotRepository(
        networkApi: NetworkApi,
        screenshotDao: ScreenshotDao
    ): ScreenshotRepository {
        return ScreenshotRepositoryImpl(networkApi, screenshotDao)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        networkApi: NetworkApi
    ): SearchRepository {
        return SearchRepositoryImpl(networkApi)
    }

}