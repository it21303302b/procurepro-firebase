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

        btnApprove.setOnClickListener {
            // Set the status to "Approved" in the database
            updateStatus("Approved")
            Toast.makeText(this, "Order approved!", Toast.LENGTH_SHORT).show()
            // Disable the buttons after clicking
            btnApprove.isEnabled = false
            btnDecline.isEnabled = false
        }

        btnDecline.setOnClickListener {
            // Set the status to "Declined" in the database
            updateStatus("Declined")
            Toast.makeText(this, "Order declined!", Toast.LENGTH_SHORT).show()
            // Disable the buttons after clicking
            btnApprove.isEnabled = false
            btnDecline.isEnabled = false
        }
    }

    private fun updateStatus(newStatus: String) {
        // Retrieve the unique key for the order based on the site manager ID
        val siteManagerId = intent.getStringExtra("siteManagerId")
        if (siteManagerId != null) {
            orderRef.orderByChild("siteManagerId").equalTo(siteManagerId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (orderSnapshot in dataSnapshot.children) {
                            val orderKey = orderSnapshot.key
                            orderRef.child(orderKey!!).child("status").setValue(newStatus)
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle the error
                        Toast.makeText(this@DirectorApproveOrder, "Database error", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}

