package com.example.procure_pro.backend

data class OrderDB(
    var siteManagerId: String? = null,
    var siteName: String? = null,
    var itemName: String? = null,
    var quantity: String? = null // Change this to String
)

