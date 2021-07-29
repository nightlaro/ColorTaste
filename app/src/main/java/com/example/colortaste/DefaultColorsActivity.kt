package com.example.colortaste

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private lateinit var buttons_container : View
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_default_colors)
        buttons_container = findViewById(R.id.default_color_container)

        sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE)

        setBackgroundColor()

        val listOfButtons = listOf<Button>(
            findViewById(R.id.red_button),
            findViewById(R.id.orange_button),
            findViewById(R.id.yellow_button),
            findViewById(R.id.green_button),
            findViewById(R.id.blue_button),
            findViewById(R.id.indigo_button),
            findViewById(R.id.violet_button)
        )
        listOfButtons.forEach { it.setOnClickListener(::onClick) }
    }

    private fun onClick(view: View) {
        val buttonBackgroundColor = view.background as ColorDrawable
        sharedPreferences.edit {
            putInt(MainActivity.COLOR_KEY, buttonBackgroundColor.color)
            apply()
        }
        finish()
    }

    private fun setBackgroundColor() {
        val backgroundColor = sharedPreferences.getInt(MainActivity.COLOR_KEY, 0)
        buttons_container.setBackgroundColor(backgroundColor)
    }
}