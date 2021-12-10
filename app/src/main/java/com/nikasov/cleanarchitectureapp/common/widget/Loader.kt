package com.nikasov.cleanarchitectureapp.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.common.extensions.dpToPx
import com.nikasov.cleanarchitectureapp.common.extensions.getResourceDrawable
import com.nikasov.cleanarchitectureapp.common.extensions.pxToDx

class Loader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        setupView()
        setupLottie()
    }

    private fun setupView() {
        background = context.getResourceDrawable(R.color.white)
    }

    private fun setupLottie() {
        val lottieView = LottieAnimationView(context).apply {
            layoutParams = LayoutParams(
                dpToPx(120f), dpToPx(120f), Gravity.CENTER
            )
            setAnimation(R.raw.loader)
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
        this.addView(lottieView)
    }

}