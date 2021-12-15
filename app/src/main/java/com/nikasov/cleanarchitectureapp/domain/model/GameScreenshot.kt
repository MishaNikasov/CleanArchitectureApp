package com.nikasov.cleanarchitectureapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameScreenshot(
    val height: Int,
    val id: Int,
    val image: String,
    val width: Int
): Parcelable