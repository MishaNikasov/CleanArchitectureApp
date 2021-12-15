package com.nikasov.cleanarchitectureapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot

@Entity(
    tableName = "screenshots",
    indices = [
        Index("image_url", unique = true)
    ]
)
data class ScreenshotEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "image_url") val image: String
) {

    fun toGameScreenshot() =
        GameScreenshot(
            id = id,
            image = image,
            isFavorite = true
        )
}