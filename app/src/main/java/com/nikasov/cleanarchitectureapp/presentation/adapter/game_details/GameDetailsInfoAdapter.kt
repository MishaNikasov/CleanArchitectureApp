package com.nikasov.cleanarchitectureapp.presentation.adapter.game_details

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemGameDetailsInfoBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.domain.model.search.FilterQuery
import com.nikasov.cleanarchitectureapp.presentation.base.BaseAdapter
import javax.inject.Inject

class GameDetailsInfoAdapter @Inject constructor(): BaseAdapter<GameDetailsInfo, GameDetailsInfoViewHolder>() {

    var onItemClick = { _: FilterQuery -> }

    override var differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameDetailsInfoViewHolder(ItemGameDetailsInfoBinding.inflate(parent.inflater(), parent, false), onItemClick)

    override fun onBindViewHolder(holder: GameDetailsInfoViewHolder, position: Int) =
        holder.bindView(list[position], position)

}