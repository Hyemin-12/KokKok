package com.example.kokkok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 상단바 숨기기
        supportActionBar?.hide()

        val intent = Intent(this, AddGiftActivity::class.java)
        startActivity(intent)
    }
}