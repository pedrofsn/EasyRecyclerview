package br.com.redcode.easyrecyclerview.sample.list

import android.view.View
import br.com.redcode.easyrecyclerview.library.adapter.BaseAdapter
import br.com.redcode.easyrecyclerview.sample.R

class AdapterString(override var click: ((String, Int) -> Unit)?) :
    BaseAdapter<String, ViewHolderString>() {

    override val layout: Int = R.layout.viewholder_string
    override fun getViewHolder(view: View) = ViewHolderString(view)
    // override fun getFilterable() = { item: String, query: String -> item.contains(other = query, ignoreCase = true) }

}

