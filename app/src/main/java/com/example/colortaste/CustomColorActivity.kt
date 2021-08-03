package com.example.colortaste

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
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
        val formLayout = LinearLayout(this)
        val buttonTitle = EditText(this)
        buttonTitle.inputType = InputType.TYPE_CLASS_TEXT
        val colorHex = EditText(this)
        colorHex.inputType = InputType.TYPE_CLASS_TEXT

        formLayout.addView(buttonTitle)
        formLayout.addView(colorHex)

        AlertDialog.Builder(this)
            .setTitle(R.string.create_custom_button_text)
            .setView(formLayout)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Add") { dialog, _ ->
                val buttonTitleString = buttonTitle.text.toString()
                val colorHexString = colorHex.text.toString()
                //filterHexString(dialog)
                val newButton = CustomButton(buttonTitleString, colorHexString)
                val adapter = customColorRecyclerView.adapter as CustomColorAdapter
                dataManager.saveButton(newButton)
                adapter.addButton(newButton)
                dialog.dismiss()

                //parse hex string into color int
                //set button's background color to color int
            }
            .create()
            .show()

    }
}