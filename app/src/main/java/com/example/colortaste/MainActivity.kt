package com.example.colortaste

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "lolhahahahalolkeklolencrypted"
        const val PREF_NAME = "drake"
    }

    lateinit var mainContainer : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainContainer = findViewById(R.id.main_layout)
        val chooseButton = findViewById<Button>(R.id.choose_color_button)
        val customColorButton = findViewById<Button>(R.id.create_custom_button_main)
        chooseButton.setOnClickListener { _ ->
            startDefaultColorsActivity()
        }
        customColorButton.setOnClickListener {
            startCustomColorActivity()
        }
    }

    private fun startCustomColorActivity() {
        val intent = Intent(this, CustomColorActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val backgroundColor = sharedPref.getInt(COLOR_KEY, 0)
        Log.d("COLOR", "backgroundColor: $backgroundColor")
        mainContainer.setBackgroundColor(backgroundColor)
    }

    private fun startDefaultColorsActivity() {
        val intent = Intent(this, DefaultColorsActivity::class.java)
        startActivity(intent)
    }
}