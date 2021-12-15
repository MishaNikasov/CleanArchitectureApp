package com.nikasov.cleanarchitectureapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikasov.cleanarchitectureapp.data.local.entity.ScreenshotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScreenshotDao {

    @Query("SELECT * FROM screenshots")
    fun getAll(): Flow<List<ScreenshotEntity>?>

    @Query("SELECT EXISTS(SELECT * FROM screenshots WHERE id = :id)")
    fun isFavorite(id: Int?): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveScreenshot(screenshotEntity: ScreenshotEntity)

    @Query("DELETE FROM screenshots WHERE id = :id")
    suspend fun removeScreenshot(id: Int)

}