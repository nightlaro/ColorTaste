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
        chooseButton.setOnClickListener { _ ->
            startDefaultColorsActivity()
        }
    }

    override fun onStart() {
        super.onStart()
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