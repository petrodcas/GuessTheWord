/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import androidx.core.content.ContextCompat



/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var binding: GameFragmentBinding

    //referencia al GameViewModel
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.currentTime.observe(viewLifecycleOwner, Observer {
            time -> if (time <= 10) colorRed(binding.timerText)
        })

        viewModel.gameEnded.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) onEndGame(view as View)
        })

        return binding.root
    }


    /** Methods for button presses */

    private fun onEndGame (view: View) {
        Snackbar.make(view, getString(R.string.snack_endGame), Snackbar.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = viewModel.score.value?:0
        view.findNavController().navigate(action)
    }

    private fun colorRed (textView: TextView) {
        Log.d("::::Coloreado", "Se va a colorear de rojo. Color actual: ${binding.timerText.currentTextColor.toString()}")
        textView.setTextColor(ContextCompat.getColor(context as Context, R.color.selected_red_color))
        Log.d("::::Coloreado", "Fin del coloreado de rojo. Color actual: ${binding.timerText.currentTextColor.toString()}")
    }

}
