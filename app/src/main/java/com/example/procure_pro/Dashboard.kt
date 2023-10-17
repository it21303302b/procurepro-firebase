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
        val userId = intent.getStringExtra("userId") // Get the user's _id from the intent
        val addOrderIntent = Intent(this, AddOrder::class.java)
        addOrderIntent.putExtra("userId", userId) // Pass the user's _id to AddOrder
        startActivity(addOrderIntent)
    }

    fun ManagePurchase(view: View) {
        val userId = intent.getStringExtra("userId") // Get the user's _id from the intent

        val manageOrderIntent = Intent(this, ManageOrder::class.java)
        manageOrderIntent.putExtra("userId", userId) // Pass the user's _id to ManageOrder
        startActivity(manageOrderIntent)
    }
    fun OrderStatus(view: View){
        val dashIntent = Intent(this,OrderStatus::class.java)
        startActivity(dashIntent)
    }

    fun AddDelivery(view: View){
        val dashIntent = Intent(this,AddDelivery::class.java)
        startActivity(dashIntent)
    }

    fun SupplierManagement(view: View){
        val dashIntent = Intent(this,OrderStatus::class.java)
        startActivity(dashIntent)
    }

    fun DeliverManagement(view: View){
        val dashIntent = Intent(this,DeliveryManagement::class.java)
        startActivity(dashIntent)
    }
}