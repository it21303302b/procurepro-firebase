package com.example.procure_pro.backend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.procure_pro.R

class DeliveryAdapter(private val deliveries: List<DeliveryDB>) : RecyclerView.Adapter<DeliveryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSite: TextView = itemView.findViewById(R.id.tvSite)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAdderess)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_delivery, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder.tvSite.text = delivery.site
        holder.tvAddress.text = delivery.address
    }

    override fun getItemCount() = deliveries.size
}
