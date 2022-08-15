package com.ouyang.androidstudytree

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.ouyang.androidstudytree.binder.MainBinder
import com.ouyang.lib_common.annotation.AnnotationMain
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var multiTypeAdapter: MultiTypeAdapter? = null

    val list = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addData()
        multiTypeAdapter = MultiTypeAdapter(list)
        var binder = MainBinder()
        binder.setOnItemClickListener { view, item ->
            when (item) {
                AnnotationMain.JNI -> {
                    startActivity(Intent(this@MainActivity, JNIActivity::class.java))
                }
                AnnotationMain.COUNTDOWNLATCH -> {
                    startActivity(Intent(this@MainActivity, CountDownLatchActivity::class.java))
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
        list.add(AnnotationMain.JNI)
        list.add(AnnotationMain.COUNTDOWNLATCH)
    }
}