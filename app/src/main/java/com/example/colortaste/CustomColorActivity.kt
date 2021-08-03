package com.example.colortaste

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomColorActivity : AppCompatActivity() {
    //lateinit var customColorRecyclerView: RecyclerView
    private lateinit var customColorAdapter: CustomColorAdapter
    private lateinit var customColorRecyclerView : RecyclerView
    private val dataManager: ButtonListDataManager = ButtonListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_color)
        val addColorButton : FloatingActionButton = findViewById(R.id.add_color_button)
        val buttonList : List<CustomButton> = dataManager.readListOfButtons()
        customColorRecyclerView = findViewById(R.id.custom_buttons_recyclerview)
        customColorAdapter = CustomColorAdapter(buttonList)
        customColorRecyclerView.layoutManager = LinearLayoutManager(this)
        customColorRecyclerView.adapter = customColorAdapter

        addColorButton.setOnClickListener {
            startDialog()
        }
    }

    private fun startDialog() {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.create_custom_color_dialog, null, false)
        val buttonTitle = dialogView.findViewById<EditText>(R.id.dialog_name_input)
        val colorHex = dialogView.findViewById<EditText>(R.id.dialog_hex_input)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Add") { dialog, _ ->
                val buttonTitleString = buttonTitle.text.toString()
                val colorHexString = colorHex.text.toString()
                Log.d("CREATE", "Title: $buttonTitle, HEX: $colorHex")
                if (filterHexString(colorHexString)) {
                    val newButton = CustomButton(buttonTitleString, colorHexString)
                    val adapter = customColorRecyclerView.adapter as CustomColorAdapter
                    dataManager.saveButton(newButton)
                    adapter.addButton(newButton)
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

    private fun filterHexString(colorHexString: String): Boolean {
        return try {
            Color.parseColor("#$colorHexString")
            true
        } catch (exception : IllegalArgumentException) {
            false
        }
    }
}