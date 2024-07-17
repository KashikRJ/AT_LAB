package com.example.quiz_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionText: TextView = view.findViewById(R.id.question_text)
        val radioGroup: RadioGroup = view.findViewById(R.id.radio_group)
        val trueButton: RadioButton = view.findViewById(R.id.true_button)
        val falseButton: RadioButton = view.findViewById(R.id.false_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.questionText.text = question.text
        holder.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            question.answer = when (checkedId) {
                R.id.true_button -> true
                R.id.false_button -> false
                else -> null
            }
        }
    }

    override fun getItemCount() = questions.size
}
