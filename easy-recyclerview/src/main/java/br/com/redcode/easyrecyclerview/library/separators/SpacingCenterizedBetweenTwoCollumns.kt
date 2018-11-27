package br.com.redcode.easyrecyclerview.library.separators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by pedrofsn on 22/02/18.
 */
class SpacingCenterizedBetweenTwoCollumns(val spacing: Int = 15) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        if (position >= 0) {
            val isItemSecondColumn = position % 2 != 0

            val left = if (isItemSecondColumn) spacing else 0
            val right = if (isItemSecondColumn) 0 else spacing

            outRect.set(left, 0, right, 0)

        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}