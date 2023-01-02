package com.example.skycoreassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SkyCoreAdapter(val context: Context, val listItem: List<Businesse>) :
    RecyclerView.Adapter<SkyCoreVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkyCoreVH {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return SkyCoreVH(view)
    }

    override fun onBindViewHolder(holder: SkyCoreVH, position: Int) {
        val items = listItem.get(position)
        Glide.with(context).load(items.image_url).into(holder.hotelImage)
        holder.hotelName.text = items.name
        holder.distance.text = items.distance.toString()
        holder.address.text = items.location.address1
        holder.status.text = items.is_closed.toString()
        holder.rating.text = items.rating.toString()

    }

    override fun getItemCount(): Int = listItem.size
}

class SkyCoreVH(item: View) : RecyclerView.ViewHolder(item) {
    val hotelImage = item.findViewById<ImageView>(R.id.iv_hotelImage)
    val hotelName = item.findViewById<TextView>(R.id.tv_hotelName)
    val distance = item.findViewById<TextView>(R.id.tv_distance)
    val address = item.findViewById<TextView>(R.id.tv_address)
    val status = item.findViewById<TextView>(R.id.tv_status)
    val rating = item.findViewById<TextView>(R.id.tv_rating)


}