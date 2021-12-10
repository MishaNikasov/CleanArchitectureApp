package com.nikasov.cleanarchitectureapp.presentation.adapter.game

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemGameBinding
import com.nikasov.cleanarchitectureapp.domain.model.Game
import javax.inject.Inject

class GameListAdapter @Inject constructor(): PagingDataAdapter<Game, GameViewHolder>(gameListCallback) {

    private val differ = AsyncListDiffer(this, gameListCallback)
//
//    val list: List<Game>
//        get() = differ.currentList
//
//    fun submitList(list: List<Game>?) {
//        if (list == null) return
//        differ.submitList(list)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameViewHolder(ItemGameBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) = holder.bindView(getItem(position), position)

}

private val gameListCallback = object : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Game, newItem: Game) = oldItem == newItem
}