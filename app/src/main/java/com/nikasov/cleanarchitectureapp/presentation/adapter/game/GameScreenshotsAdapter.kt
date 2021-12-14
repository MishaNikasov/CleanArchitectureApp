package com.nikasov.cleanarchitectureapp.presentation.adapter.game

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemGameBinding
import com.nikasov.cleanarchitectureapp.databinding.ItemScreenshotBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import javax.inject.Inject

class GameScreenshotsAdapter @Inject constructor(): PagingDataAdapter<GameScreenshot, GameScreenshotViewHolder>(gameListCallback) {

    var onScreenshotClick = { _: GameScreenshot -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameScreenshotViewHolder(ItemScreenshotBinding.inflate(parent.inflater(), parent, false), onScreenshotClick)

    override fun onBindViewHolder(holder: GameScreenshotViewHolder, position: Int) =
        holder.bindView(getItem(position), position)

}

private val gameListCallback = object : DiffUtil.ItemCallback<GameScreenshot>() {
    override fun areItemsTheSame(oldItem: GameScreenshot, newItem: GameScreenshot) = oldItem == newItem
    override fun areContentsTheSame(oldItem: GameScreenshot, newItem: GameScreenshot) = oldItem == newItem
}