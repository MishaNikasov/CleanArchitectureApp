package com.nikasov.cleanarchitectureapp.domain.model

import androidx.annotation.DrawableRes
import com.nikasov.cleanarchitectureapp.R

enum class FavoriteState(@DrawableRes val icon: Int) {
    FAVORITE(R.drawable.ic_favorite_fill),
    DEFAULT(R.drawable.ic_favorite)
}