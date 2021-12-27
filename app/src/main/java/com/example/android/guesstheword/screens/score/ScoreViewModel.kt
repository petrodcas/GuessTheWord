package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ScoreViewModel(finalScore: Int) : ViewModel() {
    //final score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // Indicates wether the event to restart the game has been launched or not
    private val _restartGameEvent = MutableLiveData<Boolean>()
    val restartGameEvent: LiveData<Boolean>
        get() = _restartGameEvent

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
        _score.value = finalScore
        _restartGameEvent.value = false
    }


    fun onPlayAgain() {
        _restartGameEvent.value = true
        onPlayAgainComplete()
    }

    private fun onPlayAgainComplete() {
        _restartGameEvent.value = false
    }

}