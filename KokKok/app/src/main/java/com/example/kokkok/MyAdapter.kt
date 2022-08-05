package com.example.kokkok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    var titles = arrayOf("one", "two", "three", "four", "five")
    var details = arrayOf("Item one", "Item two", "Item three", "Item four", "Itme five")

    var images = intArrayOf(
        R.drawable.cat,
        R.drawable.cat,
        R.drawable.cat,
        R.drawable.cat,
        R.drawable.cat
    )


    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val itemimage: ImageView = itemview.findViewById(R.id.item_image)
        val itemtitle: TextView = itemview.findViewById(R.id.item_title)
        val itemdetail: TextView = itemview.findViewById(R.id.item_detail)
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): MyViewHolder {
        var v: View = LayoutInflater.from(viewgroup.context).inflate(R.layout.card_layout, viewgroup, false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemtitle.setText(titles.get(position))
        holder.itemimage.setImageResource(images.get(position))
        holder.itemdetail.setText(details.get(position))
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}