package com.example.kokkok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView


class MyPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_page_fragment, container, false)

        val card1 = view.findViewById<CardView>(R.id.card_view1)
        val card2 = view.findViewById<CardView>(R.id.card_view2)
        val card3 = view.findViewById<CardView>(R.id.card_view3)

        card1.setOnClickListener {
            // 프로젝트 세부 사항 페이지로 이동
            val intent = Intent(view.context, GiftInfoActivity::class.java)
            view.context.startActivity(intent)
        }

        card2.setOnClickListener {
            // 프로젝트 세부 사항 페이지로 이동
            val intent = Intent(view.context, GiftInfoActivity::class.java)
            view.context.startActivity(intent)
        }

        card3.setOnClickListener {
            // 프로젝트 세부 사항 페이지로 이동
            val intent = Intent(view.context, GiftInfoActivity::class.java)
            view.context.startActivity(intent)
        }

        return view
    }
}