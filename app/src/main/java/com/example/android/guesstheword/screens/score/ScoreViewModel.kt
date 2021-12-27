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

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
        _score.value = finalScore
    }
}