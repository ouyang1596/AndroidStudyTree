package com.ouyang.androidstudytree.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ouyang.androidstudytree.R
import com.ouyang.lib_common.BaseItemViewBinder

class JetpackBinder : BaseItemViewBinder<String, JetpackBinder.ViewHolder>(), View.OnClickListener {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): JetpackBinder.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.binder_jetpack, parent, false))
    }

    override fun onBindViewHolder(holder: JetpackBinder.ViewHolder, item: String) {
        holder.btn.text = item
        holder.btn.tag = item
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn = itemView.findViewById<Button>(R.id.btn)

        init {
            btn.setOnClickListener(this@JetpackBinder)
        }
    }

    override fun onClick(v: View?) {
        //这里会插入字节码
//        Toast.makeText(ApplicationBase.instance, "hello world", Toast.LENGTH_LONG).show()
        onItemClickListener?.let {
            onItemClickListener.OnItemClick(v, v?.tag)
        }
    }
}