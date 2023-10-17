package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.procure_pro.backend.OrderDB
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateOrder : AppCompatActivity() {
    private lateinit var etSiteMgrId: EditText
    private lateinit var etSiteName: EditText
    private lateinit var etItemName: EditText
    private lateinit var etPriceOrder: EditText
    private lateinit var btnUpdateOrder: Button
    private lateinit var btnDeleteOrder: Button

    // Reference to the Firebase database
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val orderRef: DatabaseReference = database.getReference("orders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_order)
        supportActionBar?.hide()

        etSiteMgrId = findViewById(R.id.etSiteMgrId)
        etSiteName = findViewById(R.id.etSiteName)
        etItemName = findViewById(R.id.etItemName)
        etPriceOrder = findViewById(R.id.etPriceOrder)
        btnUpdateOrder = findViewById(R.id.btnUpdateOrder)
        btnDeleteOrder = findViewById(R.id.btnDeleteOrder)

        // Retrieve the details passed from ManageOrder activity
        val siteManagerId = intent.getStringExtra("siteManagerId")
        val siteName = intent.getStringExtra("siteName")
        val itemName = intent.getStringExtra("itemName")
        val quantity = intent.getStringExtra("quantity")

        // Fill out the EditText fields with the retrieved details
        etSiteMgrId.setText(siteManagerId)
        etSiteName.setText(siteName)
        etItemName.setText(itemName)
        etPriceOrder.setText(quantity)

        // Set an onClick listener for the "Update" button
        btnUpdateOrder.setOnClickListener {
            val updatedSiteManagerId = etSiteMgrId.text.toString()
            val updatedSiteName = etSiteName.text.toString()
            val updatedItemName = etItemName.text.toString()
            val updatedQuantity = etPriceOrder.text.toString()

            // Update the database with the new data
            val updatedOrder = OrderDB(updatedSiteManagerId, updatedSiteName, updatedItemName, updatedQuantity)
            if (siteManagerId != null) {
                orderRef.child(siteManagerId).setValue(updatedOrder)
                Toast.makeText(this, "Data Updated successfully!", Toast.LENGTH_SHORT).show()
            }

            // Finish the activity and return to the previous activity (ManageOrder)
            finish()
        }

        // Set an onClick listener for the "Delete" button
        btnDeleteOrder.setOnClickListener {
            // Create an AlertDialog for confirmation
            AlertDialog.Builder(this)
                .setTitle("Delete Order")
                .setMessage("Are you sure you want to delete this order?")
                .setPositiveButton("Yes") { _, _ ->
                    // User confirmed, delete the order from the database
                    if (siteManagerId != null) {
                        orderRef.child(siteManagerId).removeValue()
                        Toast.makeText(this, "Data Deleted successfully!", Toast.LENGTH_SHORT).show()
                    }

                    // Finish the activity and return to the previous activity (ManageOrder)
                    finish()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}