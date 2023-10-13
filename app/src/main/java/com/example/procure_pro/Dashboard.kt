package com.example.procure_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()
    }

    fun AddPurchase(view: View){
        val dashIntent = Intent(this,AddPurchase::class.java)
        startActivity(dashIntent)
    }

    fun ManagePurchase(view: View){
        val dashIntent = Intent(this,ManagePurchase::class.java)
        startActivity(dashIntent)
    }

    fun SupplierManagement(view: View){
        val dashIntent = Intent(this,SupplierManagement::class.java)
        startActivity(dashIntent)
    }

    fun DeliverManagement(view: View){
        val dashIntent = Intent(this,DeliveryManagement::class.java)
        startActivity(dashIntent)
    }
}