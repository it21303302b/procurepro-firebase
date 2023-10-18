package com.example.procure_pro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.example.procure_pro.backend.OrderDB
import com.example.procure_pro.backend.OrderStatusAdapter
import com.example.procure_pro.R

class OrderStatus : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderStatusAdapter: OrderStatusAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var orderRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_status)
        supportActionBar?.hide()

        // Initialize Firebase Database and the reference to the "orders" node
        database = FirebaseDatabase.getInstance()
        orderRef = database.getReference("orders")

        // Initialize RecyclerView and Adapter
        recyclerView = findViewById(R.id.rvOrderStatus)
        recyclerView.layoutManager = LinearLayoutManager(this)
        orderStatusAdapter = OrderStatusAdapter(this, mutableListOf())
        recyclerView.adapter = orderStatusAdapter

        // Query the data from the "orders" node
        orderRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val orders = mutableListOf<OrderDB>()
                for (orderSnapshot in dataSnapshot.children) {
                    val order = orderSnapshot.getValue(OrderDB::class.java)
                    order?.let {
                        orders.add(it)
                    }
                }
                orderStatusAdapter.updateData(orders)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}

