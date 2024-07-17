package com.example.q1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val getans:ArrayList<String>?=intent.getStringArrayListExtra("ans")
        val answersText = getans?.joinToString(separator = "\n") ?: "No answers provided"
        val score=intent.getIntExtra("score",0)

        val textid=findViewById<TextView>(R.id.textView2)
        textid.text="Your Score is $score/12 "

        val textid2=findViewById<TextView>(R.id.textView3)
        textid2.text="Your answers are: $answersText \n"



    }
}