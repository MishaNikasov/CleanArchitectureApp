package com.nikasov.cleanarchitectureapp.presentation.adapter.game

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.nikasov.cleanarchitectureapp.databinding.ItemGameBinding
import com.nikasov.cleanarchitectureapp.databinding.ItemScreenshotBinding
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameScreenshotViewHolder(
    private val binding: ItemScreenshotBinding,
    private val onScreenshotClick: (GameScreenshot) -> Unit
): RecyclerView.ViewHolder(binding.root), IBinder<GameScreenshot> {
    override fun bindView(model: GameScreenshot?, position: Int) {
        binding.apply {
            image.load(model?.image)
        }
    }
}