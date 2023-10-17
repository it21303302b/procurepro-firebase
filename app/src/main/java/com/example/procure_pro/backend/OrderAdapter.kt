package com.example.procure_pro.backend

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.R
import com.example.procure_pro.UpdateOrder

class OrderAdapter(private val orderList: List<OrderDB>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tvItem)
        val tvQty: TextView = itemView.findViewById(R.id.tvQty)

        init {
            // Set an item click listener here
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = orderList[position]

                    // Start the UpdateOrder activity and pass the selected order details
                    val context = itemView.context
                    val updateOrderIntent = Intent(context, UpdateOrder::class.java)
                    updateOrderIntent.putExtra("siteManagerId", currentItem.siteManagerId)
                    updateOrderIntent.putExtra("siteName", currentItem.siteName)
                    updateOrderIntent.putExtra("itemName", currentItem.itemName)
                    updateOrderIntent.putExtra("quantity", currentItem.quantity)
                    context.startActivity(updateOrderIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentItem = orderList[position]
        holder.tvItem.text = currentItem.itemName
        holder.tvQty.text = currentItem.quantity.toString()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}
