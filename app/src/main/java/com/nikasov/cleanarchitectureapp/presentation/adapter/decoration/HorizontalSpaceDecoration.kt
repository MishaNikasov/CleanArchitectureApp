package com.nikasov.cleanarchitectureapp.presentation.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikasov.cleanarchitectureapp.common.extensions.dpToPx

class HorizontalSpaceDecoration(
    private val divider: Int = dpToPx(12f)
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {
            if (parent.indexOfChild(view) != 0)
                left = divider/2
            right = divider/2
        }
    }

}