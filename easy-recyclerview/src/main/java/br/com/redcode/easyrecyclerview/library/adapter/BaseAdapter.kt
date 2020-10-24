package br.com.redcode.easyrecyclerview.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.redcode.easyrecyclerview.library.adapter.viewholder.BaseViewHolder
import br.com.redcode.easyrecyclerview.library.extension_functions.clearAndAddAll
import java.util.*

/**
 * Created by pedrofsn on 16/10/2017.
 */
abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(val filterable: ((T, query: String) -> Boolean)? = null) :
    RecyclerView.Adapter<VH>() {

    abstract var click: ((T, Int) -> Unit)?
    private val original = arrayListOf<T>()
    val list = ArrayList<T>()
    open var clickRow = true

    abstract val layout: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)
        return getViewHolder(view)
    }

    abstract fun getViewHolder(view: View): VH

    override fun onBindViewHolder(holder: VH, position: Int) = holder.populate(
        obj = getLista()[position],
        click = click,
        clickRow = clickRow
//            , custom = any
    )

    override fun getItemCount() = list.size

    fun getLista() = list

    open fun setCustomList(customList: List<T>?) {
        if (customList != null) {
            this.list.clear()
            this.list.addAll(customList)
            notifyDataSetChanged()
        }

        filterable?.let {
            if (original.isEmpty() && customList?.isNotEmpty() == true) {
                original.clearAndAddAll(customList)
            }
        }
    }

    fun addAll(novaLista: List<T>?) {
        if (novaLista != null) {
            if (itemCount == 0) {
                setCustomList(novaLista)
            } else {
                val tamanhoAtual = this.list.size
                val tamanhoNovo = novaLista.size
                val total = tamanhoAtual + tamanhoNovo

                this.list.addAll(novaLista)
                notifyItemRangeInserted(tamanhoAtual, total)
            }
        }
    }

    fun add(item: T?) {
        if (item != null) {
            val tamanhoAtual = this.list.size
            val total = tamanhoAtual + 1

            this.list.add(item)
            notifyItemRangeInserted(tamanhoAtual, total)
        }
    }

    fun isEmpty() = list.size == 0

    open fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }

    fun getItem(index: Int) = list[index]

    open fun filter(query: String?) {
        filterable?.let {
            if (query?.isNotBlank() == true) {
                setCustomList(getLista().filter { obj -> filterable.invoke(obj, query) })
            } else {
                setCustomList(original)
            }
        }
    }
}