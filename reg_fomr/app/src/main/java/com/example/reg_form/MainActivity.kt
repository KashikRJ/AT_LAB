package com.example.reg_form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name=findViewById<EditText>(R.id.a1)
        val sex_rg=findViewById<RadioGroup>(R.id.rg1)
        val chkbox=findViewById<CheckBox>(R.id.ch1)
        val spin=findViewById<Spinner>(R.id.spin)
        val submit=findViewById<Button>(R.id.sub1)

        val countries= arrayOf("Select Country", "USA", "Canada", "India", "Australia")
        spin.adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,countries)

        submit.setOnClickListener {
            val radioID=sex_rg.checkedRadioButtonId
            val rb=findViewById<RadioButton>(radioID)
            val gender=rb.text.toString()
            val name1=name.text.toString()
            val terms=chkbox.isChecked
            val coun=spin.selectedItem.toString()

            val intent=Intent(this,Result::class.java).apply {
                putExtra("name",name1)
                putExtra("gender",gender)
                putExtra("terms",terms)
                putExtra("country",coun)
        }
            startActivity(intent)




        }
    }
}