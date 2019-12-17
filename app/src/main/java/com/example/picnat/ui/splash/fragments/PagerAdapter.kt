package com.example.picnat.ui.splash.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picnat.R
import com.example.picnat.data.model.PagerM
import kotlinx.android.synthetic.main.welcome_slide.view.*

class PagerAdapter(val pageList: ArrayList<PagerM>): RecyclerView.Adapter<PagerAdapter.PagerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.welcome_slide,parent,false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int = pageList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.text.text = pageList[position].pagerDescription
    }

    class PagerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var text = itemView.welcome_text
    }
}