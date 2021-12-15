package com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemScreenshotBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.base.BaseAdapter
import javax.inject.Inject

class GameScreenshotsAdapter @Inject constructor(): BaseAdapter<GameScreenshot, GameScreenshotViewHolder>() {

    var onScreenshotClick = { _: GameScreenshot, _: Int -> }

    var cellSize: Int? = null

    override var differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameScreenshotViewHolder(
            ItemScreenshotBinding.inflate(parent.inflater(), parent, false),
            onScreenshotClick,
            cellSize
        )

    override fun onBindViewHolder(holder: GameScreenshotViewHolder, position: Int) =
        holder.bindView(list[position], position)

}
