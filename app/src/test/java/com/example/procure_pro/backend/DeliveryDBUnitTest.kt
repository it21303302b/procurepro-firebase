package com.example.procure_pro.backend

import com.example.procure_pro.backend.DeliveryDB
import org.junit.Test
import org.junit.Assert.assertEquals

class DeliveryDBUnitTest {

    @Test
    fun testDeliveryDB() {
        // Create a DeliveryDB object
        val delivery = DeliveryDB("Site A", "123 Main St", "2023-10-01", "123-456-7890")

        // Verify that the object stores data correctly
        assertEquals("Site A", delivery.site)
        assertEquals("123 Main St", delivery.address)
        assertEquals("2023-10-01", delivery.date)
        assertEquals("123-456-7890", delivery.phoneNumber)
    }
}
