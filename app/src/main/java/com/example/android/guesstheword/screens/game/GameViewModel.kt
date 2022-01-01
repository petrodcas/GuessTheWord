package com.example.android.guesstheword.screens.game

import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.android.guesstheword.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData




class GameViewModel : ViewModel() {


    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // Indicates wether the game has finished or not
    private val _gameEnded = MutableLiveData<Boolean>()
    val gameEnded: LiveData<Boolean>
        get() = _gameEnded

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    init {
        Log.i("GameViewModel", "GameViewModel creado!")
        _word.value = ""
        _score.value = 0
        _gameEnded.value = false
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
            _score.value = (score.value)?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (!wordList.isEmpty()) {
            _score.value = (_score.value)?.plus(1)
        }
        nextWord()
    }


    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
        else {
            onGameFinish()
        }
    }

    /**
     * Sets the gameEnded property to true.
     */
    fun onGameFinish() {
        _gameEnded.value = true
        onGameFinishComplete()
    }

    /**
     * Resets the gameEnded property value to false.
     */
    private fun onGameFinishComplete() {
        _gameEnded.value = false
    }

}