package com.example.mealchoice2025

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var timeSpinner: Spinner
    private lateinit var suggestionTextView: TextView
    private lateinit var resetButton: Button
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        timeSpinner = findViewById(R.id.timeSpinner)
        suggestionTextView = findViewById(R.id.suggestionTextView)
        resetButton = findViewById(R.id.resetButton)
        exitButton = findViewById(R.id.exitButton)


        val timeOptions = arrayOf(
            "Select time of day",
            "Morning",
            "Mid-morning snack",
            "Afternoon",
            "Mid-afternoon snack",
            "Dinner",
            "After Dinner snack"
        )


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = adapter


        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    val selectedTime = timeOptions[position]
                    val suggestion = getMealSuggestion(selectedTime)
                    suggestionTextView.text = suggestion
                } else {
                    suggestionTextView.text = ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                suggestionTextView.text = ""
            }
        }


        resetButton.setOnClickListener {
            timeSpinner.setSelection(0)
            suggestionTextView.text = ""
        }


        exitButton.setOnClickListener {
            finish()
            exitProcess(0)
        }
    }


    private fun getMealSuggestion(timeOfDay: String): String {
        return when (timeOfDay) {
            "Morning" -> "Start your day with a healthy breakfast:\nScrambled eggs with toast and fresh fruit or protein pack smoothie or soaked overnight oats with fresh berries or French Toast "
            "Mid-morning snack" -> "Time for a light snack:\nFresh fruit salad or yogurt with granola or Nuts of your choice"
            "Afternoon" -> "Lunch time! Try:\nA fresh sandwich with salad or any protein of your choice"
            "Mid-afternoon snack" -> "Afternoon snack:\nA slice of cake with tea or coffee "
            "Dinner" -> "Dinner suggestion:\nPasta with your favorite sauce or wrap of your choice or A Grilled chicken with vegetables"
            "After Dinner snack" -> "Sweet ending:\nIce cream or your favorite dessert "
            else -> "Please select a valid time of day"
        }
    }
}
