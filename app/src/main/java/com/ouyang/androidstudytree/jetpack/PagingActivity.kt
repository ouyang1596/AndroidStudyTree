package com.ouyang.androidstudytree.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ouyang.androidstudytree.R

class PagingActivity : AppCompatActivity() {

    var adapter: PagingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        adapter= PagingAdapter();
    }

    class PagingAdapter : PagingDataAdapter<String, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_paging, parent, false)
            return ViewHolder(itemView)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<String>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: String,
                newConcert: String
            ) = oldConcert == newConcert

            override fun areContentsTheSame(
                oldConcert: String,
                newConcert: String
            ) = oldConcert == newConcert
        }
    }
}