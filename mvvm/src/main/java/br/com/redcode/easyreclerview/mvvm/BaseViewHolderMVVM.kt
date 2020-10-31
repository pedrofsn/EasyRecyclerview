package br.com.redcode.easyreclerview.mvvm

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/*
    CREATED BY @PEDROFSN IN 31/10/20 11:25
*/

abstract class BaseViewHolderMVVM<Data, B : ViewDataBinding>(private val binding: B) : RecyclerView.ViewHolder(binding.root) {

    protected fun context() = binding.root.context

    open fun bind(data: Data) {
        binding.executePendingBindings()
    }

    open fun bind(data: Data, onClick: ((Data, Int) -> Unit)?) {
        bind(data)
        click(binding.root, data, onClick)
    }

    open fun click(view: View?, data: Data, onClick: ((Data, Int) -> Unit)?) {
        view?.setOnClickListener {
            onClick?.invoke(data, adapterPosition)
        }
    }

}