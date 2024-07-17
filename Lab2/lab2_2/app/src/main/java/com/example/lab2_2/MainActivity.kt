package com.example.lab2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText=findViewById<EditText>(R.id.editTextText)
        val encryptButton=findViewById<Button>(R.id.button)

        encryptButton.setOnClickListener{
            val text=editText.text.toString()
            val encryptText = encryptText(text)
            editText.setText(encryptText)

        }


    }
    private fun encryptText(text:String,shift:Int=3):String{
        val encrypted=StringBuilder()

        text.forEach { char->
            when (char) {
                in 'a'..'z' -> {
                    val shifted='a'+(char-'a'+shift)%26
                    encrypted.append(shifted)
                }
                in 'A'..'Z' -> {
                    val shifted='A'+(char-'A'+shift)%26
                    encrypted.append(shifted)
                }
                else -> {
                    encrypted.append(char)
                }
            }
        }
        return encrypted.toString()
    }


}