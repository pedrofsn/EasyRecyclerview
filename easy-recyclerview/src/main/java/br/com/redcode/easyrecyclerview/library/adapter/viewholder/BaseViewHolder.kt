package br.com.redcode.easyrecyclerview.library.adapter.viewholder


import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by pedrofsn on 16/10/2017.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun populate(obj: T)

    open fun populate(
        obj: T,
        click: ((T, Int) -> Unit)?,
        clickRow: Boolean = true
    ) {
        if (obj != null) {
            populate(obj)

            if (clickRow) {
                clickIn(itemView, obj, click)
            }
        }
    }

    fun clickIn(
        view: View,
        obj: T,
        click: ((T, Int) -> Unit)?
    ) {
        if (click != null) {
            view.setOnClickListener { click.invoke(obj, adapterPosition) }
        }
    }

}