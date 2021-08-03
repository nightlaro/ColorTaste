package com.example.colortaste

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class ButtonListDataManager(private val context: Context) {

    fun saveButton(button: CustomButton) {
        val sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPref.edit() {
            putString(button.title, button.color)
            apply()
        }
    }
    fun readListOfButtons(): MutableList<CustomButton> {
        val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all
        val buttonsList = mutableListOf<CustomButton>()

        for ((title, hex) in contents) {
            buttonsList.add(CustomButton(title, hex as String))
        }

        return buttonsList
    }
}