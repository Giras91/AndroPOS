package com.extrotarget.extropos.ui.product

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Simple item decoration that adds equal spacing around grid items.
 */
class GridSpacingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(spacing, spacing, spacing, spacing)
    }
}
