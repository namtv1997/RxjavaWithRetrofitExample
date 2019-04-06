package com.example.retrofit_rxjava2_example.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit_rxjava2_example.R
import com.example.retrofit_rxjava2_example.model.Android
import kotlinx.android.synthetic.main.item_recycleview.view.*

class DataAdapter(private  var item:MutableList<Android>):RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview,parent,false))
    }

    override fun getItemCount(): Int =item.size

    override fun onBindViewHolder(hodel: ViewHolder, position: Int) {
        hodel.bind(item[position])
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(item:Android) = with(itemView){
            tv_name.text=item.name
            tv_api_level.text=item.api
            tv_version.text=item.ver

        }
    }
}