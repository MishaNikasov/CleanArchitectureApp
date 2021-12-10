package com.nikasov.cleanarchitectureapp.presentation.adapter.game

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.nikasov.cleanarchitectureapp.databinding.ItemGameBinding
import com.nikasov.cleanarchitectureapp.domain.model.Game
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameViewHolder(private val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root), IBinder<Game> {
    override fun bindView(model: Game?, position: Int) {
        binding.apply {
            model ?: return@apply
            coverImage.load(model.backgroundImage) {
                crossfade(true)
                scale(Scale.FILL)
            }
            title.text = model.name
            releaseDate.text = model.released
        }
    }
}