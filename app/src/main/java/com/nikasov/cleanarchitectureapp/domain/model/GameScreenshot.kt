package com.nikasov.cleanarchitectureapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameScreenshot(
    val id: Int,
    val image: String,
    var isFavorite: Boolean = false
): Parcelable