package com.example.kokkok

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeliveryAdapter(): RecyclerView.Adapter<DeliveryAdapter.MyViewHolder>(){

    // 임시 cardView
    var titles = arrayOf("뿌링클 먹을 사람 구함~~~", "오늘 야식으로 닭발 꼬?", "치킨 먹으면서 올림픽 볼 사람✋✋", "시카고 피자 먹을 사람~~!!!", "초밥 드실래유~?")
    var details = arrayOf("2명 / 4명\n최소주문금액 : 16000원~", "1명 / 3명\n최소주문금액 : 12000원~", "3명 / 6명\n최소주문금액 : 16000원~", "2명 / 5명\n최소주문금액 : 15000원~", "1명 / 2명\n최소주문금액 : 15000원~")
    var images = intArrayOf(
        R.drawable.chicken,
        R.drawable.dakbal,
        R.drawable.chicken2,
        R.drawable.pizza,
        R.drawable.sushi
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
            val intent = Intent(holder.itemView.context, DeliveryInfoActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}