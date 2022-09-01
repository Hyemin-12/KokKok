package com.example.kokkok

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DeliveryListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.delivery_list_fragment, container, false)

        // recyclerView -> cardView를 목록으로 보여줌
        var recyclerView = view.findViewById<RecyclerView>(R.id.delivery_recyclerview) // recyclerview id
        var layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        var adapter = DeliveryAdapter()
        recyclerView.adapter = adapter

        return view
    }
}