package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.backend.OrderDB
import com.example.procure_pro.backend.OrderAdapterAdmin
import com.google.firebase.database.*

class DirectoOrderManagement : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var ordersReference: DatabaseReference
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_directo_order_management)
        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance()
        ordersReference = database.getReference("orders")
        recyclerView = findViewById(R.id.rvOrders)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val orderList = ArrayList<OrderDB>()
        val adapter = OrderAdapterAdmin(this, orderList)
        recyclerView.adapter = adapter

        ordersReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                orderList.clear()
                for (postSnapshot in snapshot.children) {
                    val order = postSnapshot.getValue(OrderDB::class.java)
                    if (order != null && order.status == "Pending") {
                        // Only add orders with "Pending" status
                        orderList.add(order)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }
}

