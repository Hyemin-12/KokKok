package com.example.kokkok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class MyPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_page_fragment, container, false)


        val itemimage1 = view.findViewById<ImageView>(R.id.item_image1)
        val itemtitle1 = view.findViewById<TextView>(R.id.item_title1)
        val itemdetail1 = view.findViewById<TextView>(R.id.item_detail1)

        val itemtitle2 = view.findViewById<TextView>(R.id.item_title2)
        val itemdetail2 = view.findViewById<TextView>(R.id.item_detail2)
        // 마이페이지 구현

        view.setOnClickListener {
            // 프로젝트 세부 사항 페이지로 이동
            val intent = Intent(view.context, GiftInfoActivity::class.java)
            view.context.startActivity(intent)
        }
        // 마이페이지 구현

        return view
    }
}