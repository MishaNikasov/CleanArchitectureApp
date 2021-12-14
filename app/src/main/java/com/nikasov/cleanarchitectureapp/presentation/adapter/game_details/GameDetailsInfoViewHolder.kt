package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.presentation.adapter.decoration.HorizontalSpaceDecoration
import com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.info_item.GameDetailsInfoItemAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameDetailsInfoViewHolder(
    private val binding: ItemGameDetailsInfoBinding,
): RecyclerView.ViewHolder(binding.root), IBinder<GameDetailsInfo> {

    override fun bindView(model: GameDetailsInfo?, position: Int) {
        binding.apply {
            model ?: return

            val gameDetailsInfoItemAdapter = GameDetailsInfoItemAdapter()

            header.text = model.header
            with(infoItemsRecycler) {
                adapter = gameDetailsInfoItemAdapter
                layoutManager = LinearLayoutManager(context).also { it.orientation = HORIZONTAL }
                addItemDecoration(HorizontalSpaceDecoration())
            }

            gameDetailsInfoItemAdapter.submitList(model.content)
        }
    }

}