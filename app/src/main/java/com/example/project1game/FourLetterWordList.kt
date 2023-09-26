package com.example.project1game
import kotlin.random.Random

class FourLetterWordList {
    companion object {
        private val wordList = listOf(
            "WORD", "STAR", "FISH", "BLUE", "FROG", "LION", "BEAR", "CAKE", "DARK", "JUMP"
            // Add more 4-letter words as needed
        )

        fun getRandomFourLetterWord(): String {
            return wordList.random()
        }
    }
}

