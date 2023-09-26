package com.example.project1game

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var targetWord: String
    private var remainingGuesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeGame()

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            handleGuess()
        }

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            initializeGame()
        }
    }

    private fun initializeGame() {
        targetWord = FourLetterWordList.getRandomFourLetterWord()
        remainingGuesses = 3

        val correctnessTextView = findViewById<TextView>(R.id.correctnessTextView)
        correctnessTextView.text = ""

        val guessesRemainingTextView = findViewById<TextView>(R.id.guessesRemainingTextView)
        guessesRemainingTextView.text = "Guesses remaining: $remainingGuesses"

        val guessEditText = findViewById<EditText>(R.id.guessEditText)
        guessEditText.text.clear()

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.isEnabled = true

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.visibility = View.GONE

        val targetWordTextView = findViewById<TextView>(R.id.targetWordTextView)
        targetWordTextView.text = "Target Word: $targetWord"
    }

    private fun handleGuess() {
        val guessEditText = findViewById<EditText>(R.id.guessEditText)
        val guess = guessEditText.text.toString().toUpperCase()

        val correctnessTextView = findViewById<TextView>(R.id.correctnessTextView)
        val correctness = checkGuess(targetWord, guess)
        correctnessTextView.text = correctness

        remainingGuesses--

        val guessesRemainingTextView = findViewById<TextView>(R.id.guessesRemainingTextView)
        guessesRemainingTextView.text = "Guesses remaining: $remainingGuesses"

        guessEditText.text.clear()

        if (correctness == "OOOO") {
            endGame(true)
        } else if (remainingGuesses == 0) {
            endGame(false)
        }
    }

    private fun endGame(isWinner: Boolean) {
        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.isEnabled = false

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.visibility = View.VISIBLE

        if (!isWinner) {
            Toast.makeText(this, "You've exceeded your number of guesses.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkGuess(targetWord: String, guess: String): String {
        if (targetWord.length != guess.length) {
            return "" // Return an empty string if lengths don't match
        }

        val correctness = StringBuilder()

        for (i in targetWord.indices) {
            if (targetWord[i] == guess[i]) {
                correctness.append('O') // Right letter in the right place
            } else if (targetWord.contains(guess[i])) {
                correctness.append('+') // Right letter in the wrong place
            } else {
                correctness.append('X') // Letter not in the target word
            }
        }

        return correctness.toString()
    }
}


