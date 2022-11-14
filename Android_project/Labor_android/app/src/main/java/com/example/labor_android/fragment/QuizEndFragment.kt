package com.example.labor_android.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.labor_android.R
import com.example.labor_android.databinding.FragmentQuizEndBinding
import com.example.labor_android.viewmodel.QuizViewModel

class QuizEndFragment : Fragment() {

    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        binding = FragmentQuizEndBinding.inflate(inflater, container, false)

        binding.correctAnswersText.text = viewModel.controller.correctAnswers.toString()
        binding.allQuestionText.text = viewModel.num_of_questions.toString()
        binding.playerName.text = viewModel.player_name

        binding.tryAgainButton.setOnClickListener {
            viewModel.resetQuiz()
            print(viewModel.count_question)
            val fragmentManager = parentFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.fragment_container_view, QuizStartFragment())
            fragmentManager.commit()
        }
        return binding.root
    }

}