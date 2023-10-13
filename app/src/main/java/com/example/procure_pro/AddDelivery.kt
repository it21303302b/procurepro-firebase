package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddDelivery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_delivery)
        supportActionBar?.hide()
    }
}