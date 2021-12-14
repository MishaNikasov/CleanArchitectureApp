package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.info_item

import androidx.recyclerview.widget.RecyclerView
import com.nikasov.cleanarchitectureapp.common.extensions.showToast
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoItemBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameDetailsInfoItemViewHolder(
    private val binding: ItemGameDetailsInfoItemBinding,
): RecyclerView.ViewHolder(binding.root), IBinder<GameDetailsInfoItem> {

    override fun bindView(model: GameDetailsInfoItem?, position: Int) {
        binding.apply {
            model ?: return
            content.text = model.name
            rootCard.setOnClickListener {
                root.context.showToast(model.javaClass.toString())
            }

            when (model) {
                is GameDetailsInfoItem.Genre -> {

                }
                else -> {}
            }
        }
    }

}