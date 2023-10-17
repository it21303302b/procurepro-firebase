package com.example.procure_pro.backend

import java.io.Serializable

data class OrderDB(
    var siteManagerId: String? = null,
    var siteName: String? = null,
    var itemName: String? = null,
    var quantity: String? = null,
    var status: String? = null
) : Serializable