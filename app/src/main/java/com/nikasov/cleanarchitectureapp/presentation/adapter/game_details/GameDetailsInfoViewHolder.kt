package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nikasov.cleanarchitectureapp.R
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.common.extensions.showToast
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem
import com.nikasov.cleanarchitectureapp.presentation.base.IBinder

class GameDetailsInfoViewHolder(
    private val binding: ItemGameDetailsInfoBinding,
): RecyclerView.ViewHolder(binding.root), IBinder<GameDetailsInfo> {

    override fun bindView(model: GameDetailsInfo?, position: Int) {
        binding.apply {
            model ?: return

            val context = root.context
            header.text = model.header
            with(infoItemsRecycler) {
                model.content.forEach { infoItem ->
                    addChip(context, infoItem)
                }
            }
        }
    }

    private fun ChipGroup.addChip(context: Context, infoItem: GameDetailsInfoItem){
        val chip = inflater().inflate(R.layout.item_info_chip, this, false) as Chip
        chip.apply {
            id = View.generateViewId()
            text = infoItem.name
            isClickable = true
            isCheckable = true
            isCheckedIconVisible = false
            isFocusable = true

            setOnClickListener {
                context.showToast(infoItem.javaClass.toString())
            }

            when (infoItem) {

            }

            addView(this)
        }
    }
}