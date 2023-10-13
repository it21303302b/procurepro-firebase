package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SupplierManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier_management)
        supportActionBar?.hide()
    }
}