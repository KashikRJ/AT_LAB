package com.example.reg_form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val res=findViewById<TextView>(R.id.textViewResult)

        val name=intent.getStringExtra("name")
        val gender = intent.getStringExtra("gender")
        val termsAccepted = intent.getBooleanExtra("termsAccepted", false)
        val country = intent.getStringExtra("country")

        val resultText = """
            Name: $name
            Gender: $gender
            Country: $country
            Terms Accepted: ${if (termsAccepted) "Yes" else "No"}
        """.trimIndent()

        res.text = resultText






    }
}