package com.example.procure_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.procure_pro.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.mongodb.Credentials
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import org.bson.Document

class MainActivity : AppCompatActivity() {
    lateinit var app: App
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(this)   //initialise realm to connect mongodb
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        supportActionBar?.hide()

        val appId = "devicesync-agtgn" // connect to database with realm

        val config = AppConfiguration.Builder(appId)
            .build()

        app = App(config)
    }

    fun log(view: View) {
        val username = binding?.etsitemgrName?.text.toString()
        val password = binding?.etEnterPassword?.text.toString()

        // Create a Document with username and password fields
        val payload = Document("username", username).append("password", password)

        val credentials = Credentials.customFunction(payload)

        Toast.makeText(this, "Checking credentials. Please wait!", Toast.LENGTH_SHORT).show()

        app.loginAsync(credentials) { user ->
            if (user.isSuccess) {
                // Successfully logged in
                val loggedInUser = user.get()
                val userId = loggedInUser.id // Get the user's _id

                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()

                // Check if the username is "Admin" and redirect to DirectorDash
                if (username == "Admin") {
                    val directorDashIntent = Intent(this, DirectorDash::class.java)
                    startActivity(directorDashIntent)
                } else {
                    // If not "Admin," proceed to the Dashboard
                    val dashIntent = Intent(this, Dashboard::class.java)
                    dashIntent.putExtra("userId", userId) // Pass the user's _id to AddOrder
                    startActivity(dashIntent)
                }
            } else {
                // Failed to login, handle the error
                val error = user.getError()
                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show()
            }
        }
    }



}
