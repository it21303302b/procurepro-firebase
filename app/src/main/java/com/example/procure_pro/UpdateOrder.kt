package com.example.procure_pro

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.example.procure_pro.backend.OrderDB

class UpdateOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_order)
        supportActionBar?.hide()

        val orderKey = intent.getStringExtra("orderKey")

        if (orderKey == null) {
            Toast.makeText(this, "Invalid order key.", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            val etSiteMgrId = findViewById<EditText>(R.id.etSiteMgrId)
            val etSiteName = findViewById<EditText>(R.id.etSiteName)
            val etItemName = findViewById<EditText>(R.id.etItemName)
            val etPriceOrder = findViewById<EditText>(R.id.etQtyOrder)
            val btnUpdateOrder = findViewById<Button>(R.id.btnUpdateOrder)
            val btnDeleteOrder = findViewById<Button>(R.id.btnDeleteOrder)

            // Initialize Firebase database reference
            val orderRef = FirebaseDatabase.getInstance().getReference("orders").child(orderKey)

            orderRef.get().addOnSuccessListener { dataSnapshot ->
                val orderData = dataSnapshot.getValue(OrderDB::class.java)
                if (orderData != null) {
                    etSiteMgrId.setText(orderData.siteManagerId)
                    etSiteName.setText(orderData.siteName)
                    etItemName.setText(orderData.itemName)
                    etPriceOrder.setText(orderData.quantity.toString())

                    btnUpdateOrder.setOnClickListener {
                        val siteName = etSiteName.text.toString()
                        val itemName = etItemName.text.toString()
                        val quantityString = etPriceOrder.text.toString()

                        if (siteName.isNotEmpty() && itemName.isNotEmpty() && quantityString.isNotEmpty()) {
                            // Update the values directly in the order reference
                            orderRef.child("siteName").setValue(siteName)
                            orderRef.child("itemName").setValue(itemName)
                            orderRef.child("quantity").setValue(quantityString)

                            Toast.makeText(this, "Data updated successfully!", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
                        }
                    }

                    btnDeleteOrder.setOnClickListener {
                        // Show a confirmation dialog before deleting
                        AlertDialog.Builder(this)
                            .setTitle("Delete Order")
                            .setMessage("Are you sure you want to delete this order?")
                            .setPositiveButton("Yes") { _, _ ->
                                // User confirmed, proceed with deletion
                                orderRef.removeValue().addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Data deleted successfully!", Toast.LENGTH_SHORT).show()
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Data deletion failed.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            .setNegativeButton("No", null) // User canceled, do nothing
                            .show()
                    }
                } else {
                    Toast.makeText(this, "Invalid order data.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}

