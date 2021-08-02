package com.example.colortaste

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomColorDataManager(private val context : Context) {
    fun saveButtonColor(colorButtonData: CustomButton) {
        val sharedPreferences : SharedPreferences = PreferenceManager
            .getDefaultSharedPreferences(context)
        sharedPreferences.edit {
            putString(colorButtonData.title, colorButtonData.color)
        }
    }
}