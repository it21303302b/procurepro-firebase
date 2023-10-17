package com.example.procure_pro.backend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.R

class OrderStatusAdapter(private val context: Context, private var orders: List<OrderDB>) : RecyclerView.Adapter<OrderStatusAdapter.OrderStatusViewHolder>() {

    inner class OrderStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.tvItem)
        val siteNameTextView: TextView = itemView.findViewById(R.id.tvSiteName)
        val siteManagerIdTextView: TextView = itemView.findViewById(R.id.tvsitemgrID)
        val quantityTextView: TextView = itemView.findViewById(R.id.tvQty)
        val statusTextView: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderStatusViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order_status, parent, false)
        return OrderStatusViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderStatusViewHolder, position: Int) {
        val currentOrder = orders[position]
        holder.itemTextView.text = currentOrder.itemName
        holder.siteNameTextView.text = currentOrder.siteName
        holder.siteManagerIdTextView.text = currentOrder.siteManagerId
        holder.quantityTextView.text = currentOrder.quantity
        holder.statusTextView.text = currentOrder.status

        // Handle item click here, similar to your other adapter
        holder.itemView.setOnClickListener {
            // Implement click handling if needed
        }
    }

    // Add this method to update the data in the adapter
    fun updateData(newData: List<OrderDB>) {
        orders = newData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}
