package com.example.kokkok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DeliveryInfoActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_info_activity)
        supportActionBar?.hide()

    }
}