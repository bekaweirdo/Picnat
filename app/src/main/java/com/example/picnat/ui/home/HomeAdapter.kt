package com.example.picnat.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picnat.R
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_home_item.view.*

class HomeAdapter(val context: Context?, private val userList: FirebaseUser?): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_item,parent,false)
        return HomeViewHolder(itemView)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.username.text = userList?.email

        // TODO
        //  holder?.profilePicture
    }



    class HomeViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var username = itemView.username
        var profilePicture = itemView.userProfile
    }
}