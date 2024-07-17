package com.example.quiz_app

data class Question(val text: String, val correctAnswer: Boolean, var answer: Boolean? = null)
