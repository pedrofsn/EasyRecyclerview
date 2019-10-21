package br.com.redcode.easyrecyclerview.sample

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.redcode.easyrecyclerview.library.extension_functions.setCustomAdapter
import br.com.redcode.easyrecyclerview.sample.list.AdapterString
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val names = listOf("Pedro", "Luatane", "João Pedro", "José").sortedBy { it }
    private val click = { name: String, index: Int -> onClickItem(name, index) }
    private val adapter by lazy { AdapterString(click) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.setCustomList(names)
        recyclerView.setCustomAdapter(adapter)
    }

    private fun onClickItem(name: String, index: Int) {
        AlertDialog.Builder(this)
            .setTitle(name)
            .setMessage("Clicked in position $index")
            .setCancelable(false)
            .setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog?.dismiss() }
            .show()
    }
}
