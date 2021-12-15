package com.nikasov.cleanarchitectureapp.di

import android.content.Context
import androidx.room.Room
import com.nikasov.cleanarchitectureapp.common.utils.Constants
import com.nikasov.cleanarchitectureapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, Constants.DATABASE_NAME
    ).build()


    @Provides
    @Singleton
    fun provideScreenshotDao(room: AppDatabase) = room.userDao()
}