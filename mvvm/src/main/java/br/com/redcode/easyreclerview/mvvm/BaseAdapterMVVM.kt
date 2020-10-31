package br.com.redcode.easyreclerview.mvvm


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/*
    CREATED BY @PEDROFSN IN 31/10/20 11:25
*/

abstract class BaseAdapterMVVM<Data, B : ViewDataBinding>(var onItemClickListener: ((Data, Int) -> Unit)? = null) : RecyclerView.Adapter<BaseViewHolderMVVM<Data, B>>() {

    abstract var click: ((Data, Int) -> Unit)?
    private val items = arrayListOf<Data>()
    abstract val layout: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolderMVVM<Data, B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil.inflate(inflater, layout, parent, false)
        return getViewHolder(binding)
    }

    abstract fun getViewHolder(binding: B): BaseViewHolderMVVM<Data, B>

    override fun onBindViewHolder(holder: BaseViewHolderMVVM<Data, B>, position: Int) = holder.bind(
        data = items[position],
        onClick = click
    )

    override fun getItemCount() = items.size

    open fun setData(customList: List<Data>?) {
        if (customList != null) {
            if(items.isNotEmpty()) {
                items.clear()
            }
            items.addAll(customList)
            notifyDataSetChanged()
        }
    }

    fun addAll(newcontent: List<Data>?) {
        if (newcontent != null) {
            if (itemCount == 0) {
                setData(newcontent)
            } else {
                val currentSize = items.size
                val newSize = newcontent.size
                val total = currentSize + newSize

                items.addAll(newcontent)
                notifyItemRangeInserted(currentSize, total)
            }
        }
    }

    fun add(item: Data?) {
        if (item != null) {
            val currentSize = items.size
            val total = currentSize + 1

            items.add(item)
            notifyItemRangeInserted(currentSize, total)
        }
    }

    fun remove(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    open fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getItem(index: Int) = items[index]
    fun getItemIndex(item: Data) = items.indexOfFirst { it == item }

}