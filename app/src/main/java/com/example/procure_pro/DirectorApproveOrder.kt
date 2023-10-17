package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.procure_pro.R
import com.example.procure_pro.backend.OrderDB
import com.google.firebase.database.*

class DirectorApproveOrder : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var orderRef: DatabaseReference
    private lateinit var selectedOrderKey: String // To store the key of the selected order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_director_approve_order)
        supportActionBar?.hide()

        database = FirebaseDatabase.getInstance()
        orderRef = database.getReference("orders")

        val siteManagerId = intent.getStringExtra("siteManagerId")
        val siteName = intent.getStringExtra("siteName")
        val itemName = intent.getStringExtra("itemName")
        val quantity = intent.getStringExtra("quantity")

        val etSiteMgrId = findViewById<EditText>(R.id.etSiteMgrId)
        val etSiteName = findViewById<EditText>(R.id.etSiteName)
        val etItemName = findViewById<EditText>(R.id.etItemName)
        val etQtyOrder = findViewById<EditText>(R.id.etQtyOrder)

        etSiteMgrId.setText(siteManagerId)
        etSiteName.setText(siteName)
        etItemName.setText(itemName)
        etQtyOrder.setText(quantity)

        etSiteMgrId.isEnabled = false
        etSiteName.isEnabled = false
        etItemName.isEnabled = false
        etQtyOrder.isEnabled = false

        val btnApprove = findViewById<Button>(R.id.btnApprove)
        val btnDecline = findViewById<Button>(R.id.btnDecline)

        // Find the order key based on siteManagerId
        findOrderKey(siteManagerId)

        btnApprove.setOnClickListener {
            // Set the status to "Approved" for the selected order
            if (selectedOrderKey != null) {
                updateStatus("Approved")
                Toast.makeText(this, "Order approved!", Toast.LENGTH_SHORT).show()
                // Disable the buttons after clicking
                btnApprove.isEnabled = false
                btnDecline.isEnabled = false
            } else {
                Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show()
            }
        }

        btnDecline.setOnClickListener {
            // Set the status to "Declined" for the selected order
            if (selectedOrderKey != null) {
                updateStatus("Declined")
                Toast.makeText(this, "Order declined!", Toast.LENGTH_SHORT).show()
                // Disable the buttons after clicking
                btnApprove.isEnabled = false
                btnDecline.isEnabled = false
            } else {
                Toast.makeText(this, "Order not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun findOrderKey(siteManagerId: String?) {
        if (siteManagerId != null) {
            orderRef.orderByChild("siteManagerId").equalTo(siteManagerId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (orderSnapshot in dataSnapshot.children) {
                            // Retrieve the key of the first order with the matching siteManagerId
                            selectedOrderKey = orderSnapshot.key.toString()
                            break // Exit the loop after finding the key
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle the error
                        Toast.makeText(this@DirectorApproveOrder, "Database error", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun updateStatus(newStatus: String) {
        // Update the status for the selected order
        if (selectedOrderKey != null) {
            orderRef.child(selectedOrderKey).child("status").setValue(newStatus)
        }
    }
}

