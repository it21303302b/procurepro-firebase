package com.example.procure_pro.backend

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.R
import com.example.procure_pro.UpdateOrder

class OrderAdapter(private val orderList: List<OrderDB>, private val orderKeys: List<String>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tvItem)
        val tvQty: TextView = itemView.findViewById(R.id.tvQty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentItem = orderList[position]
        val currentKey = orderKeys[position]
        holder.tvItem.text = currentItem.itemName
        holder.tvQty.text = currentItem.quantity.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateOrder::class.java)
            intent.putExtra("orderData", currentItem)
            intent.putExtra("orderKey", currentKey)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}
