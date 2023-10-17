package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.backend.DeliveryAdapter
import com.example.procure_pro.backend.DeliveryDB
import com.google.firebase.database.*

class DeliveryManagement : AppCompatActivity() {
    private lateinit var rvDeliveries: RecyclerView
    private lateinit var deliveryAdapter: DeliveryAdapter
    private val deliveriesList = mutableListOf<DeliveryDB>()

    // Firebase database reference
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_management)
        supportActionBar?.hide()

        rvDeliveries = findViewById(R.id.rvDeliveries)
        rvDeliveries.layoutManager = LinearLayoutManager(this)
        deliveryAdapter = DeliveryAdapter(deliveriesList)
        rvDeliveries.adapter = deliveryAdapter

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("deliveries")

        // Listen for changes in the Firebase database
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                deliveriesList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val delivery = postSnapshot.getValue(DeliveryDB::class.java)
                    delivery?.let {
                        deliveriesList.add(it)
                    }
                }
                deliveryAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        })
    }
}
