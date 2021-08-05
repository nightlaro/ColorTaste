package com.example.colortaste

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class ButtonListDataManager(private val context: Context) {

    fun saveButton(color: CustomColor) {
        val sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPref.edit {
            putString(color.title, color.color)
        }
    }
    fun readListOfButtons(): List<CustomColor> {
        val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        return contents.map {
            CustomColor(it.key, it.value as String)
        }
    }
}