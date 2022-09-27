package com.example.kokkok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 상단바 숨기기
        supportActionBar?.hide()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, AddGiftFragment())
        transaction.commit()
    }
}