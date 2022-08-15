package com.ouyang.lib_common;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.drakeet.multitype.ItemViewBinder;


/**
 * @author : ryan
 */
public abstract class BaseItemViewBinder<T, VH extends RecyclerView.ViewHolder> extends ItemViewBinder<T, VH> {

    protected OnItemClickListener onItemClickListener;

    protected OnLongItemClickListener onLongItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void OnItemClick(View view, T item);
    }

    public interface OnLongItemClickListener<T> {
        void OnLongItemClick(View view, T item);
    }
}
