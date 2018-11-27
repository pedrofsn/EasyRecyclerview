package br.com.redcode.easyrecyclerview.library.extension_functions

import java.util.*

/**
 * Created by pedrofsn on 30/11/2017.
 */
fun <E> ArrayList<E>.addNonNullElements(elements: List<E?>?) {
    if (elements != null && elements.isNotEmpty()) {
        elements.forEach { it?.let { add(it) } }
    }
}

fun <E> ArrayList<E>.clearAndAddAll(elements: List<E?>?) {
    clear()
    addNonNullElements(elements)
}