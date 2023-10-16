package com.example.procure_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class AddOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        supportActionBar?.hide()

        val userId = intent.getStringExtra("userId")

        // Assuming you have a reference to the etSiteMgrId field in your layout
        val etSiteMgrId = findViewById<EditText>(R.id.etSiteMgrId)

        // Set the user ID and make the field non-editable
        etSiteMgrId.setText(userId)
        etSiteMgrId.isFocusable = false
        etSiteMgrId.isFocusableInTouchMode = false
    }
}

