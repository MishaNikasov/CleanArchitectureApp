package com.nikasov.cleanarchitectureapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikasov.cleanarchitectureapp.data.local.dao.ScreenshotDao
import com.nikasov.cleanarchitectureapp.data.local.entity.ScreenshotEntity

@Database(entities = [ScreenshotEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ScreenshotDao
}