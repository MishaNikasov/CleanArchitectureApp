package com.nikasov.cleanarchitectureapp.common.di

import com.nikasov.cleanarchitectureapp.data.remote.NetworkApi
import com.nikasov.cleanarchitectureapp.data.repository.GamesRepositoryImpl
import com.nikasov.cleanarchitectureapp.domain.repository.GamesRepository
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

}