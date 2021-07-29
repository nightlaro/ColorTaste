package com.example.colortaste

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.edit

class DefaultColorsActivity : AppCompatActivity() {

    lateinit var buttons_container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_colors)
        buttons_container = findViewById(R.id.default_color_container)
        val redButton = findViewById<Button>(R.id.red_button)
        val orangeButton = findViewById<Button>(R.id.orange_button)
        val yellowButton = findViewById<Button>(R.id.yellow_button)
        val greenButton = findViewById<Button>(R.id.green_button)
        val blueButton = findViewById<Button>(R.id.blue_button)
        val indigoButton = findViewById<Button>(R.id.indigo_button)
        val violetButton = findViewById<Button>(R.id.violet_button)
        //damn is there a better way of doing this?
        redButton.setOnClickListener { view ->
            onClick(view)
        }
        orangeButton.setOnClickListener { view ->
            onClick(view)
        }
        yellowButton.setOnClickListener { view ->
            onClick(view)
        }
        greenButton.setOnClickListener { view ->
            onClick(view)
        }
        blueButton.setOnClickListener { view ->
            onClick(view)
        }
        indigoButton.setOnClickListener { view ->
            onClick(view)
        }
        violetButton.setOnClickListener { view ->
            onClick(view)
        }

    }

    private fun onClick(view: View) {
        var buttonBackgroundColor = view.background as ColorDrawable
        buttons_container.setBackgroundColor(buttonBackgroundColor.color)
        val sharedPreferences = getSharedPreferences(MainActivity.COLOR_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putInt(MainActivity.COLOR_KEY, buttonBackgroundColor.color)
            apply()
        }
        finish()
    }
}