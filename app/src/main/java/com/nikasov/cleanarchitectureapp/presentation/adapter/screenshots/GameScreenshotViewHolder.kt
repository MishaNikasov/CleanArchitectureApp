package com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nikasov.cleanarchitectureapp.databinding.ItemScreenshotBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameScreenshotViewHolder(
    private val binding: ItemScreenshotBinding,
    private val onScreenshotClick: (GameScreenshot) -> Unit
): RecyclerView.ViewHolder(binding.root), IBinder<GameScreenshot> {
    override fun bindView(model: GameScreenshot?, position: Int) {
        binding.apply {
            model ?: return
            image.load(model.image)
            cardRoot.setOnClickListener {
                onScreenshotClick(model)
            }
        }
    }
}