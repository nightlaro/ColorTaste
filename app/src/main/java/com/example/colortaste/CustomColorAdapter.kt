package com.example.colortaste

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import java.lang.Integer.parseInt
import java.lang.Long.parseLong

class CustomColorAdapter(private val data: List<CustomButton>) :
    RecyclerView.Adapter<CustomColorAdapter.CustomButtonViewHolder>() {

    class CustomButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val customViewButton : Button = view.findViewById(R.id.custom_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_color_view_holder, parent,false)

        return CustomButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomButtonViewHolder, position: Int) {
        val title = data[position].title
        val colorHexValue = data[position].color
        holder.customViewButton.text = title
        holder.customViewButton.setBackgroundColor(Color.parseColor("#${colorHexValue}"))
    }

    override fun getItemCount(): Int {
        return data.size
    }
}