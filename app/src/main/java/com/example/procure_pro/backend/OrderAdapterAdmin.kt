package com.example.procure_pro.backend

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.DirectorApproveOrder
import com.example.procure_pro.R

class OrderAdapterAdmin(private val context: Context, private val orders: List<OrderDB>) : RecyclerView.Adapter<OrderAdapterAdmin.OrderViewHolder>() {

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.tvItem)
        val quantityTextView: TextView = itemView.findViewById(R.id.tvQty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentOrder = orders[position]
        holder.itemNameTextView.text = currentOrder.itemName
        holder.quantityTextView.text = currentOrder.quantity

        holder.itemView.setOnClickListener {
            // Handle item click here
            val intent = Intent(context, DirectorApproveOrder::class.java)
            intent.putExtra("siteManagerId", currentOrder.siteManagerId)
            intent.putExtra("siteName", currentOrder.siteName)
            intent.putExtra("itemName", currentOrder.itemName)
            intent.putExtra("quantity", currentOrder.quantity)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}
