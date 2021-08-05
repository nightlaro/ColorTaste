package com.example.colortaste

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomColorActivity : AppCompatActivity(), CustomColorAdapter.CustomButtonClickListener {

    private lateinit var customColorAdapter: CustomColorAdapter
    private lateinit var customColorRecyclerView : RecyclerView
    private val dataManager: ButtonListDataManager = ButtonListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_color)
        val addColorButton : FloatingActionButton = findViewById(R.id.add_color_button)
        val colorList : List<CustomColor> = dataManager.readListOfButtons()
        customColorRecyclerView = findViewById(R.id.custom_buttons_recyclerview)
        customColorAdapter = CustomColorAdapter(colorList, this)
        customColorRecyclerView.layoutManager = LinearLayoutManager(this)
        customColorRecyclerView.adapter = customColorAdapter

        val sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE)
        val backgroundColor = sharedPreferences.getInt(MainActivity.COLOR_KEY, 0)
        val mainContainer = findViewById<View>(R.id.custom_color_layout)
        mainContainer.setBackgroundColor(backgroundColor)

        addColorButton.setOnClickListener {
            startDialog()
        }
    }

    private fun startDialog() {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.create_custom_color_dialog, null, false)
        val buttonTitleInput = dialogView.findViewById<EditText>(R.id.dialog_name_input)
        val colorHexInput = dialogView.findViewById<EditText>(R.id.dialog_hex_input)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Add") { dialog, _ ->
                val buttonTitleString = buttonTitleInput.text.toString()
                val colorHexString = colorHexInput.text.toString()

                if (filterHexString(colorHexString)) {
                    val newButton = CustomColor(buttonTitleString, colorHexString)
                    val adapter = customColorRecyclerView.adapter as CustomColorAdapter
                    dataManager.saveButton(newButton)
                    adapter.buttonList += newButton
                    dialog.dismiss()
                } else {
                    val text = "Make sure you enter a correct HEX code, google it if you have to " +
                            "It would be nice to have a color picker right? but that would take a lot of time maybe.."
                    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                }
            }
            .create()
            .show()

    }

    override fun onClick(view: View) {
        val buttonBackgroundColor = view.background as ColorDrawable
        val sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putInt(MainActivity.COLOR_KEY, buttonBackgroundColor.color)
            apply()
        }
        finish()
    }

    private fun filterHexString(colorHexString: String): Boolean {
        return try {
            Color.parseColor("#$colorHexString")
            true
        } catch (exception : IllegalArgumentException) {
            false
        }
    }
}