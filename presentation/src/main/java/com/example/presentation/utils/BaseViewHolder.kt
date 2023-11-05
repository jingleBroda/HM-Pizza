package com.example.presentation.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T: Any>(item:ViewBinding) : RecyclerView.ViewHolder(item.root) {
    abstract fun bind(data: T)
}