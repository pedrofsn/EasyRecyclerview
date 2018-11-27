package br.com.redcode.easyrecyclerview.library.domain

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by pedrofsn on 09/01/18.
 */
interface EndlessRecyclerView {

    fun resetState()
    fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}