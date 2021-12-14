package com.nikasov.cleanarchitectureapp.presentation.adapter.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikasov.cleanarchitectureapp.common.extensions.dpToPx

class HorizontalSpaceDecoration(
    private val divider: Int = 12
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val dividerInDp = dpToPx(divider.toFloat())

        with(outRect) {
            left = if (parent.indexOfChild(view) != 0)
                dividerInDp/2
            else
                0
            right = dividerInDp/2
        }
    }

}