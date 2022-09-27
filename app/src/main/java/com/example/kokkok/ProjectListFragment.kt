package com.example.kokkok

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class ProjectListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.project_list_fragment, container, false)

        // 탭바 추가
        val tabLayout = view.findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("선물"))
        tabLayout.addTab(tabLayout.newTab().setText("배달"))

        // 시작할 때 -> 선물 목록 보이게
        childFragmentManager
            .beginTransaction()
            .replace(R.id.list_fragment, GiftListFragment())
            .commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab?.position
                when(pos) {
                    // 0 -> 선물
                    0 -> {
                        Log.d("mytag", "???")
                        childFragmentManager
                            .beginTransaction()
                            .replace(R.id.list_fragment, GiftListFragment())
                            .commit()
                    }
                    // 1 -> 배달
                    1 -> {
                        Log.d("mytag", "뿡")
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        return view
    }
}