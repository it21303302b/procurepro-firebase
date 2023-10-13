package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ManagePurchase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_purchase)
        supportActionBar?.hide()
    }
}