package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var resultTextView: TextView
    var lastNumeric: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.textViewResult)
        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val buttonFive: Button = findViewById(R.id.buttonFive)
        val buttonSix: Button = findViewById(R.id.buttonSix)
        val buttonSeven: Button = findViewById(R.id.buttonSeven)
        val buttonEight: Button = findViewById(R.id.buttonEight)
        val buttonNine: Button = findViewById(R.id.buttonNine)
        val buttonZero: Button = findViewById(R.id.buttonZero)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonClear: Button = findViewById(R.id.buttonClear)

        // Initialize buttons and set their onClickListeners
        val buttons = arrayOf(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive,
            buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero)

        buttons.forEach { button ->
            button.setOnClickListener { onDigitPressed((it as Button).text.toString()) }
        }

        buttonAdd.setOnClickListener { onOperationPressed("+") }
        buttonSubtract.setOnClickListener { onOperationPressed("-") }
        buttonMultiply.setOnClickListener { onOperationPressed("*") }
        buttonDivide.setOnClickListener { onOperationPressed("/") }

        buttonClear.setOnClickListener {
            resultTextView.text = ""
            lastNumeric = false
        }

        buttonEquals.setOnClickListener {
            onEquals()
        }
    }

    private fun onDigitPressed(digit: String) {
        resultTextView.append(digit)
        lastNumeric = true
    }

    private fun onOperationPressed(operation: String) {
        if (lastNumeric && !isOperatorAdded(resultTextView.text.toString())) {
            resultTextView.append(operation)
            lastNumeric = false
        }
    }

    private fun onEquals() {
        if (lastNumeric) {
            val expression = ExpressionBuilder(resultTextView.text.toString()).build()
            try {
                val result = expression.evaluate()
                resultTextView.text = result.toString()
            } catch (ex: Exception) {
                resultTextView.text = "Error"
                lastNumeric = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}
