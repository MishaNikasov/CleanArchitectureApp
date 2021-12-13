package com.nikasov.cleanarchitectureapp.presentation.fragment.test.use_case

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DiModule {

    @Provides
    fun provideDataSource(): DataSource = DataSourceImpl()

}