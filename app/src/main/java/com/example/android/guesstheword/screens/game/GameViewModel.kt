package com.example.android.guesstheword.screens.game

import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.android.guesstheword.R
import androidx.lifecycle.MutableLiveData




class GameViewModel : ViewModel() {
    companion object {
        const val NO_MORE_WORDS: String = ""
    }


    // The current word
    val word = MutableLiveData<String>()

    // The current score
    var score = MutableLiveData<Int>()

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>


    init {
        Log.i("GameViewModel", "GameViewModel creado!")
        word.value = ""
        score.value = 0
        resetList()
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destruido!")
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        if (!wordList.isEmpty()) {
            score.value = (score.value)?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (!wordList.isEmpty()) {
            score.value = (score.value)?.plus(1)
        }
        nextWord()
    }


    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word.value = wordList.removeAt(0)
        }
        else {
            word.value = NO_MORE_WORDS
        }
    }

}