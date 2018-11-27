package br.com.redcode.easyrecyclerview.sample.list

import android.view.View
import android.widget.TextView
import br.com.redcode.easyrecyclerview.library.adapter.viewholder.BaseViewHolder
import br.com.redcode.easyrecyclerview.sample.R


class ViewHolderString(itemView: View) : BaseViewHolder<String>(itemView) {

    private val textView by lazy { itemView.findViewById<TextView>(R.id.textView) }

    override fun populate(obj: String) {
        textView.text = obj
    }
}