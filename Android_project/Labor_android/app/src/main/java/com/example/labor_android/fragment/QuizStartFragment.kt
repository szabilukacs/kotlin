package com.example.labor_android.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.labor_android.R
import com.example.labor_android.databinding.FragmentQuizStartBinding
import com.example.labor_android.viewmodel.QuizViewModel

class QuizStartFragment : Fragment(R.layout.fragment_quiz_start) {

    private val TAG: String = javaClass.simpleName
    private lateinit var viewModel: QuizViewModel

    lateinit var binding: FragmentQuizStartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        val startButton = binding.startButtonQuiz
        viewModel.player_name = "asd"
        startButton.setOnClickListener {
            Log.d(TAG, "Clicked on button")
            if (viewModel.player_name.isNotBlank()) {
                println(viewModel.player_name)
                viewModel.player_name = binding.nameInput.text.toString()
                println(viewModel.player_name)
                println(binding.nameInput.toString())
                Log.d(TAG, "ITT")
                viewModel.controller.quiz(viewModel.num_of_questions) // letrehozza az elejen a kerdeseket
                val fragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container_view, QuestionFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit()
            } else {
                Toast.makeText(activity, "Type your name", Toast.LENGTH_LONG).show()
            }
        }
    }

}