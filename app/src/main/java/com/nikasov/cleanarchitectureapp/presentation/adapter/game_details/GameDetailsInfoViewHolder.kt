package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem
import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameDetailsInfoViewHolder(
    private val binding: ItemGameDetailsInfoBinding,
    private val onItemClick: (FilterQuery) -> Unit,
): RecyclerView.ViewHolder(binding.root), IBinder<GameDetailsInfo> {

    override fun bindView(model: GameDetailsInfo?, position: Int) {
        binding.apply {
            model ?: return

            header.text = model.header
            with(infoItemsRecycler) {
                model.content.forEach { infoItem ->
                    addChip(infoItem)
                }
            }
        }
    }

    private fun ChipGroup.addChip(infoItem: GameDetailsInfoItem){
        val chip = inflater().inflate(R.layout.item_info_chip, this, false) as Chip
        chip.apply {
            id = View.generateViewId()
            text = infoItem.name
            isClickable = true
            isCheckable = true
            isCheckedIconVisible = false
            isFocusable = true

            setOnClickListener {
                val query = when (infoItem) {
                    is GameDetailsInfoItem.Genre -> FilterQuery.Genres(infoItem.slug)
                    is GameDetailsInfoItem.Developer -> FilterQuery.Developers(infoItem.slug)
                    is GameDetailsInfoItem.Tag -> FilterQuery.Tags(infoItem.slug)
                    else -> FilterQuery.Empty
                }
                onItemClick(query)
            }

            addView(this)
        }
    }
}