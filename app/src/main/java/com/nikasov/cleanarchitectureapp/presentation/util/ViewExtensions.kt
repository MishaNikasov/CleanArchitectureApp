package com.nikasov.cleanarchitectureapp.presentation.util

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import coil.load
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.common.extensions.dpToPx
import com.nikasov.cleanarchitectureapp.common.extensions.getResourceColor
import com.nikasov.cleanarchitectureapp.domain.model.ParentPlatform

fun LinearLayout.addPlatforms(
    context: Context,
    platforms: List<ParentPlatform>,
    @DrawableRes iconColor: Int = R.color.white,
    iconSize: Int = 25,
    padding: Int = 10
) {
    removeAllViews()
    platforms.forEach { platform ->
        val iconView = ImageView(context)
        iconView.layoutParams = LinearLayout.LayoutParams(
            dpToPx(iconSize.toFloat()),
            dpToPx(iconSize.toFloat()),
            1f
        )
        iconView.setColorFilter(context.getResourceColor(iconColor))
        iconView.scaleType = ImageView.ScaleType.FIT_CENTER
        iconView.setPadding(dpToPx(padding.toFloat()), 0, 0, 0)
        iconView.load(platform.icon)
        addView(iconView)
    }
}