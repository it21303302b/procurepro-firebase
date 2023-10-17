package com.example.procure_pro.backend

import com.example.procure_pro.backend.OrderDB
import org.junit.Test
import org.junit.Assert.assertEquals

class OrderDBUnitTest {

    @Test
    fun testOrderDB() {
        // Create an OrderDB object
        val order = OrderDB("Manager1", "Site A", "Item X", "10")

        // Verify that the object stores data correctly
        assertEquals("Manager1", order.siteManagerId)
        assertEquals("Site A", order.siteName)
        assertEquals("Item X", order.itemName)
        assertEquals("10", order.quantity)
    }

    @Test
    fun testDefaultValues() {
        // Create an OrderDB object with default values
        val order = OrderDB()

        // Verify that the default values are null
        assertEquals(null, order.siteManagerId)
        assertEquals(null, order.siteName)
        assertEquals(null, order.itemName)
        assertEquals(null, order.quantity)
    }
}
