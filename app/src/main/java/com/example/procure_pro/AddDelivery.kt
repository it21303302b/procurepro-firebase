package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.procure_pro.backend.DeliveryDB

class AddDelivery : AppCompatActivity() {
    private lateinit var etSite: EditText
    private lateinit var etAddress: EditText
    private lateinit var etDate: EditText
    private lateinit var etPhoneNo: EditText
    private lateinit var btnAddDelivery: Button

    // Firebase database reference
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_delivery)
        supportActionBar?.hide()

        etSite = findViewById(R.id.etSite)
        etAddress = findViewById(R.id.etAddress)
        etDate = findViewById(R.id.etDate)
        etPhoneNo = findViewById(R.id.etPhoneNo)
        btnAddDelivery = findViewById(R.id.btnAddDelivery)

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("deliveries")

        // Set an OnClickListener for the "btnAddDelivery" button
        btnAddDelivery.setOnClickListener {
            // Retrieve data from EditText fields
            val site = etSite.text.toString()
            val address = etAddress.text.toString()
            val date = etDate.text.toString()
            val phoneNo = etPhoneNo.text.toString()

            // Create a DeliveryDB object with the data
            val deliveryData = DeliveryDB(site, address, date, phoneNo)

            // Push the data to Firebase database
            val deliveryId = databaseReference.push().key
            deliveryId?.let {
                databaseReference.child(it).setValue(deliveryData)
            }

            // Clear EditText fields
            etSite.text.clear()
            etAddress.text.clear()
            etDate.text.clear()
            etPhoneNo.text.clear()

            // Display a toast message for successful insertion
            Toast.makeText(this, "Insert Successful", Toast.LENGTH_SHORT).show()
        }
    }
}


