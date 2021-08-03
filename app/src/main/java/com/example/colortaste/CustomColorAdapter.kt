package com.example.colortaste

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import java.lang.Integer.parseInt
import java.lang.Long.parseLong

class CustomColorAdapter(data: List<CustomButton>) :
    RecyclerView.Adapter<CustomColorAdapter.CustomButtonViewHolder>() {
    var buttonList = data
        set(value) {
            field = value
            Log.d("COLOR", "Setting new value $field , OLD: $value")
            notifyDataSetChanged()
        }

    class CustomButtonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val customViewButton : Button = view.findViewById(R.id.custom_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomButtonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_color_view_holder, parent,false)

        return CustomButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomButtonViewHolder, position: Int) {
        val title = buttonList[position].title
        val colorHexValue = buttonList[position].color
        Log.d("COLOR", "Setting title: $title, HEX: $colorHexValue")
        holder.customViewButton.text = title
        holder.customViewButton.setBackgroundColor(Color.parseColor("#${colorHexValue}"))
    }

    override fun getItemCount(): Int {
        Log.d("SIZE", "${buttonList.size}")
        return buttonList.size
    }

    fun addButton(button: CustomButton) {
        buttonList = buttonList + button
        notifyDataSetChanged()
    }
}