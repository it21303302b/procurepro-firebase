package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DeliveryManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_management)
        supportActionBar?.hide()
    }
}