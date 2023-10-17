package com.example.procure_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.example.procure_pro.backend.OrderDB

class AddOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        supportActionBar?.hide()

        val userId = intent.getStringExtra("userId")

        // Assuming you have references to the EditText fields and the button in your layout
        val etSiteName = findViewById<EditText>(R.id.etSiteName)
        val etItemName = findViewById<EditText>(R.id.etItemName)
        val etQtyOrder = findViewById<EditText>(R.id.etQtyOrder)
        val btnAddOrder = findViewById<Button>(R.id.btnAddOrder)

        // Set the user ID and make the etSiteMgrId field non-editable
        val etSiteMgrId = findViewById<EditText>(R.id.etSiteMgrId)
        etSiteMgrId.setText(userId)
        etSiteMgrId.isFocusable = false
        etSiteMgrId.isFocusableInTouchMode = false

        // Initialize Firebase database reference
        val database = FirebaseDatabase.getInstance()
        val orderRef = database.getReference("orders")

        btnAddOrder.setOnClickListener {
            // Get data from EditText fields
            val siteName = etSiteName.text.toString()
            val itemName = etItemName.text.toString()
            val quantityString = etQtyOrder.text.toString()

            if (siteName.isNotEmpty() && itemName.isNotEmpty() && quantityString.isNotEmpty()) {
                // Create an OrderDB object with quantity as a string and initialize "status" to "pending"
                val order = OrderDB(userId, siteName, itemName, quantityString, "Pending")

                // Push the order data to Firebase database
                val orderKey = orderRef.push().key
                if (orderKey != null) {
                    orderRef.child(orderKey).setValue(order)

                    Toast.makeText(this, "Data inserted successfully!", Toast.LENGTH_SHORT).show()

                    // Clear EditText fields after submitting
                    etSiteName.text.clear()
                    etItemName.text.clear()
                    etQtyOrder.text.clear()
                } else {
                    Toast.makeText(this, "Failed to create a new order.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
