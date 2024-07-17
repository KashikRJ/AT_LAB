package com.example.lab2_3

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colorbutton=findViewById<Button>(R.id.button)
        val rootLayout=findViewById<ConstraintLayout>(R.id.rootlayout)
        colorbutton.setOnClickListener {
            val randomColor= Color.argb(255, Random.nextInt(256),Random.nextInt(256),Random.nextInt(256))
            rootLayout.setBackgroundColor(randomColor)
        }
    }
}