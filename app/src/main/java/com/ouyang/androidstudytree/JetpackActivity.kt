package com.ouyang.androidstudytree

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.ouyang.androidstudytree.binder.JetpackBinder
import com.ouyang.androidstudytree.jetpack.PagingActivity
import com.ouyang.lib_common.annotation.AnnotationJetpack
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class JetpackActivity : AppCompatActivity() {
    var multiTypeAdapter: MultiTypeAdapter? = null

    val list = ArrayList<Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        addData()
        multiTypeAdapter = MultiTypeAdapter(list)
        var binder = JetpackBinder()
        binder.setOnItemClickListener { view, item ->
            when (item) {
                AnnotationJetpack.PAGING -> {
                    startActivity(Intent(this@JetpackActivity, PagingActivity::class.java))
                }
            }
        }
        multiTypeAdapter?.register(String::class, binder)
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        recyclerView.adapter = multiTypeAdapter
    }

    private fun addData() {
        list.add(AnnotationJetpack.PAGING)
    }
}