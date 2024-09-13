package com.missouristate.chapter_two

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.missouristate.chapter_two.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true),
    )

    private var currentIndex = 0

    //lateinit allows initializing a not-null property outside of a constructor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //BINDING SET UP
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //https://developer.android.com/develop/ui/views/notifications/snackbar/action

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        //exercise 3
        //ref https://stackoverflow.com/questions/14785443/is-there-an-expression-using-modulo-to-do-backwards-wrap-around-reverse-overfl
        binding.prevButton.setOnClickListener {
            currentIndex = (currentIndex + (questionBank.size - 1)) % questionBank.size
            updateQuestion()
        }

        //Exercise 2
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }

    //refactored code
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer (userAnswer:Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct
        } else {
            R.string.incorrect
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}