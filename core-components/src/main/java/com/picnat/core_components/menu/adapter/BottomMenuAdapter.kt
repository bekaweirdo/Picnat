package com.picnat.core_components.menu.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BottomMenuAdapter<T: MenuViewHolder> : RecyclerView.Adapter<T>() {
    var adapterListener: MenuAdapterListener? = null
}

open interface MenuAdapterListener{
    fun onItemClickListener(position: Int, data: Any?)
}

open class MenuViewHolder(view: View): RecyclerView.ViewHolder(view)
