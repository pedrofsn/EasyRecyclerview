package br.com.redcode.easyrecyclerview.library.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by pedrofsn on 13/04/16.
 */
class RecyclerViewEmptySupport : RecyclerView {

    private var emptyView: View? = null
    private val emptyObserver = object : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            if (adapter != null) {
                emptyView?.let {
                    if (adapter?.itemCount == 0) {
                        it.visibility = View.VISIBLE
                        this@RecyclerViewEmptySupport.visibility = View.GONE
                    } else {
                        it.visibility = View.GONE
                        this@RecyclerViewEmptySupport.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)

        adapter?.apply {
            try {
                registerAdapterDataObserver(emptyObserver)
            } catch (e: Exception) {
                unregisterAdapterDataObserver(emptyObserver)
                registerAdapterDataObserver(emptyObserver)
            }
        }
    }

    fun showHideEmpty() = emptyObserver.onChanged()

    fun setEmptyView(emptyView: View?) {
        emptyView?.let { this.emptyView = emptyView }
    }
}