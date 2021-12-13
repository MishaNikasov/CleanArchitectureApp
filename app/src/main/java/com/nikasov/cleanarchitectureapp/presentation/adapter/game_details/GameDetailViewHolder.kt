package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details

import androidx.recyclerview.widget.RecyclerView
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameDetailViewHolder(
    private val binding: ItemGameDetailsInfoBinding,
): RecyclerView.ViewHolder(binding.root), IBinder<GameDetailsInfo> {

    override fun bindView(model: GameDetailsInfo?, position: Int) {
        binding.apply {
            model ?: return
            header.text = model.header
            content.text = model.content
        }
    }

}