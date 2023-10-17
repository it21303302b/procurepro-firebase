package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.backend.OrderAdapter
import com.example.procure_pro.backend.OrderDB
import com.google.firebase.database.*

class ManageOrder : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_order)
        supportActionBar?.hide()

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.rvOrders)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from Firebase and populate the RecyclerView
        val userId = intent.getStringExtra("userId")
        val database = FirebaseDatabase.getInstance()
        val orderRef = database.getReference("orders")

        val orderList = ArrayList<OrderDB>()
        val adapter = OrderAdapter(orderList)
        recyclerView.adapter = adapter

        // Query Firebase database for orders where siteManagerId matches the current user's userId
        val query: Query = orderRef.orderByChild("siteManagerId").equalTo(userId)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                orderList.clear()
                for (snapshot in dataSnapshot.children) {
                    val order = snapshot.getValue(OrderDB::class.java)
                    if (order != null) {
                        orderList.add(order)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors
            }
        })
    }
}
