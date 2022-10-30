package com.example.labor_android.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.labor_android.R

class QuizStartFragment : Fragment(R.layout.fragment_start_quiz) {
    private val TAG: String = javaClass.simpleName

    fun onCreate(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val startButton = view.findViewById<Button>(R.id.start_button_quiz)

        startButton.setOnClickListener {
            this.findNavController().navigate(R.id.question_fragment)
        }
    }
}