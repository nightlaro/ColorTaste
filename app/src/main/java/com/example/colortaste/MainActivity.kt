package com.example.colortaste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chooseButton = findViewById<Button>(R.id.choose_color_button)
        chooseButton.setOnClickListener { _ ->
            startDefaultColorsActivity()
        }
    }

    fun startDefaultColorsActivity() {
        val intent = Intent(this, DefaultColorsActivity::class.java)
        startActivity(intent)
    }
}