package com.example.mealchoice2025

import android.os.Bundle
import android.text.method.LinkMovementMethod
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
    private lateinit var linksTextView: TextView
    private lateinit var resetButton: Button
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeSpinner = findViewById(R.id.timeSpinner)
        suggestionTextView = findViewById(R.id.suggestionTextView)
        linksTextView = findViewById(R.id.linksTextView)
        resetButton = findViewById(R.id.resetButton)
        exitButton = findViewById(R.id.exitButton)

        linksTextView.movementMethod = LinkMovementMethod.getInstance()

        val timeOptions = arrayOf(
            "Select time of day",
            "Morning",
            "Mid-morning",
            "Afternoon",
            "Mid-afternoon",
            "Dinner",
            "After Dinner"
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
                    linksTextView.text = getRecipeLinks(selectedTime)
                } else {
                    suggestionTextView.text = ""
                    linksTextView.text = ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                suggestionTextView.text = ""
                linksTextView.text = ""
            }
        }

        resetButton.setOnClickListener {
            timeSpinner.setSelection(0)
            suggestionTextView.text = ""
            linksTextView.text = ""
        }

        exitButton.setOnClickListener {
            finish()
            exitProcess(0)
        }
    }

    private fun getMealSuggestion(timeOfDay: String): String {
        return when (timeOfDay) {
            "Morning" -> "Start your day with a healthy breakfast:\nScrambled eggs with toast and fresh fruit or protein pack smoothie or soaked overnight oats with fresh berries"
            "Mid-morning" -> "Time for a light snack:\nFresh fruit salad or yogurt with granola or Nuts"
            "Afternoon" -> "Lunch time! Try:\nA fresh sandwich with salad or any protein of your choice"
            "Mid-afternoon" -> "Afternoon snack:\nA slice of cake with tea or coffee"
            "Dinner" -> "Dinner suggestion:\nPasta with your favorite sauce or wrap of your choice"
            "After Dinner" -> "Sweet ending:\nIce cream or your favorite dessert"
            else -> "Please select a valid time of day"
        }
    }

    private fun getRecipeLinks(timeOfDay: String): String {
        return when (timeOfDay) {
            "Morning" -> "Healthy Breakfast Recipes:\n" +
                    "https://www.loveandlemons.com/healthy-breakfast-ideas//\n" +
                    "Smoothie Recipes:\nhttps://www.allrecipes.com/recipes/138/drinks/smoothies/"
            "Mid-morning" -> "Healthy Snack Ideas:\n" +
                    "https://za.pinterest.com/homykhan/mid-morning-snacks/"
            "Afternoon" -> "Quick Lunch Recipes:\n" +
                    "https://www.delish.com/cooking/recipe-ideas/g3034/quick-work-lunch-ideas/"
            "Mid-afternoon" -> "Tea Time Snacks:\n" +
                    "https://za.pinterest.com/artoftea/tea-time-snacks/"
            "Dinner" -> "Easy Dinner Ideas:\n" +
                    "https://za.pinterest.com/basilmomma/easy-dinner-recipes/\n" +
                    "Pasta Recipes:\nhttps://za.pinterest.com/search/pins/?q=easy%20pasta%20recipes&rs=ac&len=10&source_id=pMDrEsex&eq=easy%20pasta&etslf=7177"
            "After Dinner" -> "Dessert Recipes:\n" +
                    "https://www.taste.com.au/baking/galleries/top-50-desserts/rysx9rys"
            else -> ""
        }
    }
}
