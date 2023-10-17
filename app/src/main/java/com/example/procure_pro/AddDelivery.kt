package com.example.procure_pro

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.procure_pro.backend.DeliveryDB
import java.text.SimpleDateFormat
import java.util.*

class AddDelivery : AppCompatActivity() {
    private lateinit var etSite: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnPickDate: Button
    private lateinit var etPhoneNo: EditText
    private lateinit var btnAddDelivery: Button

    // Firebase database reference
    private lateinit var databaseReference: DatabaseReference

    // Calendar instance for date picker
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_delivery)
        supportActionBar?.hide()

        etSite = findViewById(R.id.etSite)
        etAddress = findViewById(R.id.etAddress)
        btnPickDate = findViewById(R.id.btnPickDate)
        etPhoneNo = findViewById(R.id.etPhoneNo)
        btnAddDelivery = findViewById(R.id.btnAddDelivery)

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("deliveries")

        // Set an OnClickListener for the "btnPickDate" button to show the date picker dialog
        btnPickDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Set an OnClickListener for the "btnAddDelivery" button to add data to the database
        btnAddDelivery.setOnClickListener {
            // Retrieve data from EditText fields
            val site = etSite.text.toString()
            val address = etAddress.text.toString()
            val date = btnPickDate.text.toString() // Use the selected date from the button
            val phoneNo = etPhoneNo.text.toString()

            // Check if any of the fields are empty
            if (site.isEmpty() || address.isEmpty() || date == "Select Date" || phoneNo.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a DeliveryDB object with the data
            val deliveryData = DeliveryDB(site, address, date, phoneNo)

            // Push the data to Firebase database
            val deliveryId = databaseReference.push().key
            deliveryId?.let {
                databaseReference.child(it).setValue(deliveryData)
            }

            // Clear EditText fields and set the date button text to "Select Date"
            etSite.text.clear()
            etAddress.text.clear()
            btnPickDate.text = "Select Date"
            etPhoneNo.text.clear()

            // Display a toast message for successful insertion
            Toast.makeText(this, "Delivery added successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val selectedDate = dateFormat.format(calendar.time)
                btnPickDate.text = selectedDate
            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }
}


