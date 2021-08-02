package com.example.colortaste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class CustomColorActivity : AppCompatActivity() {
    lateinit var customColorRecyclerView: RecyclerView
    lateinit var customColorAdapter: CustomColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_color)
    }
}