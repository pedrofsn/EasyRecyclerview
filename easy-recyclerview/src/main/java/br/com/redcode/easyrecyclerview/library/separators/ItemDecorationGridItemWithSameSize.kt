package br.com.redcode.easyrecyclerview.library.separators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by pedrofsn on 08/02/18.
 */
class ItemDecorationGridItemWithSameSize(
    val collumns: Int = 0,
    private val spacing: Int = 0,
    private val includeEdge: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % collumns // item column

        if (includeEdge) {
            outRect.left =
                spacing - column * spacing / collumns // spacing - column * ((1f / collumns) * spacing)
            outRect.right =
                (column + 1) * spacing / collumns // (column + 1) * ((1f / collumns) * spacing)

            if (position < collumns) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / collumns // column * ((1f / collumns) * spacing)
            outRect.right = spacing - (column + 1) * spacing /
                    collumns // spacing - (column + 1) * ((1f /    collumns) * spacing)
            if (position >= collumns) {
                outRect.top = spacing // item top
            }
        }
    }
}