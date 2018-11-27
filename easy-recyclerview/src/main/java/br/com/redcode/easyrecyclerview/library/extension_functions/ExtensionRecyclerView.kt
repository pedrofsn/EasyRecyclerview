package br.com.redcode.easyrecyclerview.library.extension_functions

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.redcode.easyrecyclerview.library.R
import br.com.redcode.easyrecyclerview.library.helpers.EndlessRecyclerViewScrollListener
import br.com.redcode.easyrecyclerview.library.separators.MyDividerItemDecorator

/**
 * Created by pedrofsn on 24/11/2017.
 */
fun RecyclerView.setCustomAdapter(
        adapter: RecyclerView.Adapter<*>,
        layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
        divider: Boolean = false,
        xmlDivider: Boolean = false
) {
    setLayoutManager(layoutManager)
    setAdapter(adapter)
    setHasFixedSize(true)
    isFocusable = false

    if (divider) {
        addDivider(xmlDivider.not())
    }
}

fun RecyclerView.addDivider(xmlSeparator: Boolean) {
    if (xmlSeparator) {
        ContextCompat.getDrawable(context, R.drawable.divider_recyclerview)?.let {
            val myDivider = MyDividerItemDecorator(it)
            addItemDecoration(myDivider)
        }
    } else {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        addItemDecoration(divider)
    }
}

fun RecyclerView.handleNestedScrolling() {
    onFlingListener = object : RecyclerView.OnFlingListener() {
        override fun onFling(velocityX: Int, velocityY: Int): Boolean {
            dispatchNestedFling(velocityX.toFloat(), velocityY.toFloat(), false)
            return false
        }
    }
}

fun RecyclerView.handlePagination(
        clearDataApdater: () -> Unit,
        updatePage: (actualPage: Int) -> Unit,
        layoutManager: LinearLayoutManager,
        loadMore: () -> Unit
): EndlessRecyclerViewScrollListener {
    val scrollListener: EndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            updatePage.invoke(page)
            loadMore.invoke()
        }

        override fun resetState() {
            super.resetState()
            updatePage.invoke(0)
            clearDataApdater.invoke()
        }
    }

    addOnScrollListener(scrollListener)

    return scrollListener
}