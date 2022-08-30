package com.example.kokkok

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GiftListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.gift_list_fragment, container, false)

        // recyclerView -> cardView를 목록으로 보여줌
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview) // recyclerview id
        var layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        var adapter = MyAdapter()
        recyclerView.adapter = adapter

        return view
    }
}