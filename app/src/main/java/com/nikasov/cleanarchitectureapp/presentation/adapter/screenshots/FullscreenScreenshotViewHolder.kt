package com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nikasov.cleanarchitectureapp.databinding.ItemFullscreenScreenshotBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class FullscreenScreenshotViewHolder(
    private val binding: ItemFullscreenScreenshotBinding
): RecyclerView.ViewHolder(binding.root), IBinder<GameScreenshot> {
    override fun bindView(model: GameScreenshot?, position: Int) {
        model ?: return
        with(binding) {
            screenshot.load(model.image)
        }
    }
}