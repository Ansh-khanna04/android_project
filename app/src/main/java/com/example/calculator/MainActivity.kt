package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Space
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
class MainActivity : AppCompatActivity() {
   private lateinit var operationView : TextView
    private lateinit var resultView : TextView
    private lateinit var allClear : Button
    private lateinit var backSpace: Button
    private lateinit var equalbutton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        operationView = findViewById(R.id.workingTV)
        resultView = findViewById(R.id.resultTV)
        allClear = findViewById(R.id.allClear)
        backSpace = findViewById(R.id.backSpace)
        allClear.setOnClickListener {
            operationView.text=""
            resultView.text=""
        }
        backSpace.setOnClickListener {
            val currentText = operationView.text.toString()
            //checking if there is any text to delete
            if(currentText.isNotEmpty()){
                val newText = currentText.substring(0,currentText.length-1)
                //setting the updated text to the text view
                operationView.text = newText
            }
        }
        //Defining a common onclickListener for number Buttons
        val numberOnclickListener = View.OnClickListener { v ->
            if (v is Button) {
                val number = v.text.toString() //Get the text from the clicked button
                val currentText = operationView.text.toString() // Get the current text in the TextView
                val newText = currentText + number // Append the clicked number to the current text
                operationView.text = newText // Set the updated text to the TextView
            }
        }
        // Array to store button IDs
        val buttonIds = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.button_decimal,R.id.add,R.id.subtract,R.id.multiply,R.id.divide
        )
        // Array to store the initialized buttons
        val buttons = Array<Button>(buttonIds.size) { index ->
            findViewById<Button>(buttonIds[index])
        }
         // Set OnClickListener for all buttons using a loop
        buttons.forEach { button ->
            button.setOnClickListener(numberOnclickListener)
        }
        equalbutton = findViewById(R.id.equals)
        equalbutton.setOnClickListener {
            val expression = operationView.text.toString()
            val result = ExpressionBuilder(expression).build().evaluate()
            resultView.text = "$result"
        }




















    }
}