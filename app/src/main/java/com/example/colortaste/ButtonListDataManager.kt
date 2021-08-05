package com.example.colortaste

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class ButtonListDataManager(private val context: Context) {

    fun saveButton(color: CustomColor) {
        val sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPref.edit() {
            putString(color.title, color.color)
            apply()
        }
    }
    fun readListOfButtons(): MutableList<CustomColor> {
        val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all
        val buttonsList = mutableListOf<CustomColor>()

        for ((title, hex) in contents) {
            buttonsList.add(CustomColor(title, hex as String))
        }

        return buttonsList
    }
}