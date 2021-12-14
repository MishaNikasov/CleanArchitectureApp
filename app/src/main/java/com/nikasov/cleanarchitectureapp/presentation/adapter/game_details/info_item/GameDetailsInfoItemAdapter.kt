package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.info_item

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoItemBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfoItem
import com.nikasov.cleanarchitectureapp.presentation.base.BaseAdapter
import javax.inject.Inject

class GameDetailsInfoItemAdapter @Inject constructor(): BaseAdapter<GameDetailsInfoItem, GameDetailsInfoItemViewHolder>() {

    override var differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameDetailsInfoItemViewHolder(ItemGameDetailsInfoItemBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: GameDetailsInfoItemViewHolder, position: Int) {
        holder.bindView(list[position], position)
    }

}