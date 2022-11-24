package com.example.kokkok

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GiftAdapter(): RecyclerView.Adapter<GiftAdapter.MyViewHolder>(){

    // 임시 cardView
    var titles = arrayOf("\uD83D\uDC23유진이 생일선물 살 사람\uD83D\uDC23", "혜민 생일 D-7\uD83C\uDF82", "2-3 담임쌤 생신 선물\uD83C\uDF81", "비야 생선 살 사람 구함~!", "\uD83D\uDE4A부모님 어버이날 선물\uD83D\uDE4A")
    var details = arrayOf("2명 / 5명\n13000원 / 68000원", "3명 / 6명\n30000원 / 60000원", "4명 / 12명\n40000원 / 120000원", "28명 / 30명\n280000원 / 300000원", "1명 / 3명\n33000원 / 100000원")
    var images = intArrayOf(
        R.drawable.youjin,
        R.drawable.hyemin,
        R.drawable.gd,
        R.drawable.biya,
        R.drawable.parents
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
        // cardView 구성
        holder.itemtitle.setText(titles.get(position))
        holder.itemimage.setImageResource(images.get(position))
        holder.itemdetail.setText(details.get(position))

        holder.itemView.setOnClickListener {
            // 프로젝트 세부 사항 페이지로 이동
            val intent = Intent(holder.itemView.context, GiftInfoActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}