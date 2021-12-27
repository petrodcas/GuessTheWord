package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import android.util.Log

class ScoreViewModel(finalScore: Int) : ViewModel() {
    //final score
    var score = finalScore
    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }
}