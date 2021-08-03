package com.example.colortaste

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class CustomColorAdapter(data: List<CustomButton>, private val listener: CustomButtonClickListener) :
    RecyclerView.Adapter<CustomColorAdapter.CustomButtonViewHolder>() {
    var buttonList = data
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface CustomButtonClickListener {
        fun onClick(view: View)
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
        holder.customViewButton.text = title
        holder.customViewButton.setBackgroundColor(Color.parseColor("#${colorHexValue}"))
        holder.customViewButton.setOnClickListener {
            listener.onClick(holder.customViewButton)
        }
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

}