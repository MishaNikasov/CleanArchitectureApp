package com.nikasov.cleanarchitectureapp.presentation.adapter.screenshots

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import com.nikasov.cleanarchitectureapp.common.extensions.inflater
import com.nikasov.cleanarchitectureapp.databinding.ItemFullscreenScreenshotBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameScreenshot
import com.nikasov.cleanarchitectureapp.presentation.base.BaseAdapter
import javax.inject.Inject

class FullscreenScreenshotAdapter @Inject constructor(): BaseAdapter<GameScreenshot, FullscreenScreenshotViewHolder>() {

    override var differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FullscreenScreenshotViewHolder(ItemFullscreenScreenshotBinding.inflate(parent.inflater(), parent, false))

    override fun onBindViewHolder(holder: FullscreenScreenshotViewHolder, position: Int) {
        holder.bindView(list[position], position)
    }

}