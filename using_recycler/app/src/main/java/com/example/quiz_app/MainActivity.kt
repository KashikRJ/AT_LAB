package com.example.quiz_app


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

import android.widget.Button
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var questionAdapter: QuestionAdapter
    private val questions = listOf(
        Question("Question 1: True or False?", true),
        Question("Question 2: True or False?", false),
        Question("Question 3: True or False?",false),
        Question("Question 4: True or False?",true),
        Question("Question 5: True or False?",false),
        Question("Question 6: True or False?",true),
        Question("Question 7: True or False?",false),
        Question("Question 8: True or False?",true),
        Question("Question 9: True or False?",false),
        Question("Question 10: True or False?",true),
        Question("Question 11: True or False?",false),
        Question("Question 12: True or False?",true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.questions_recycler_view)
        questionAdapter = QuestionAdapter(questions)
        recyclerView.adapter = questionAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val submitButton = findViewById<Button>(R.id.submit_button)
        submitButton.setOnClickListener {
            calculateScore()
        }
    }

    private fun calculateScore() {
        var score = 0
        questions.forEach {
            if (it.answer != null && it.answer == it.correctAnswer) {
                score++
            }
        }
        // Optionally, navigate to a results activity or show the score in a dialog
        Toast.makeText(this, "Your score is $score out of ${questions.size}", Toast.LENGTH_LONG).show()
    }
}



