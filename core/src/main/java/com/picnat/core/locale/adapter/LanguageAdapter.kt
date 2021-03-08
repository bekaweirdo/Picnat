package com.picnat.core.locale.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.picnat.core.R
import com.picnat.core.locale.Language
import com.picnat.core_components.extensions.inflate
import com.picnat.core_components.menu.adapter.BottomMenuAdapter
import com.picnat.core_components.menu.adapter.MenuViewHolder

class LanguageAdapter(
    private val languages: List<Language>,
    var selectedLanguage: Language
): BottomMenuAdapter<LanguageAdapter.ViewHolder>() {
    class ViewHolder(view: View):MenuViewHolder(view){
        val languageImageView: ImageView = view.findViewById(R.id.languageImageView)
        val languageTextView: TextView = view.findViewById(R.id.languageTextView)
        val rootContainer: ConstraintLayout = view.findViewById(R.id.rootContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.language_list_view_item)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = languages[position]
        Glide.with(holder.itemView.context).load(item.imageId).into(holder.languageImageView)
        holder.apply {
            languageTextView.text = item.displayName
            rootContainer.setOnClickListener {
                adapterListener?.onItemClickListener(position,item)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount() = languages.size
}