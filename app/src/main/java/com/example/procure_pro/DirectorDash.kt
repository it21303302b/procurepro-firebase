package com.example.procure_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DirectorDash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_director_dash)
        supportActionBar?.hide()
    }

    fun ManageOrder(view: View){
        val dashIntent = Intent(this,DirectoOrderManagement::class.java)
        startActivity(dashIntent)
    }

    fun OrderStatus(view: View){
        val dashIntent = Intent(this,OrderStatus::class.java)
        startActivity(dashIntent)
    }

    fun DeliveryManagement(view: View){
        val dashIntent = Intent(this,DeliveryManagement::class.java)
        startActivity(dashIntent)
    }
}