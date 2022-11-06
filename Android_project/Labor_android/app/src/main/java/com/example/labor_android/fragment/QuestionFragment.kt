package com.example.labor_android.fragment

import ItemController
import ItemRepository
import ItemService
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.labor_android.R
import com.example.labor_android.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {
    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    private lateinit var binding: FragmentQuestionBinding
    private lateinit var viewModel: QuizViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]
        binding = FragmentQuestionBinding.inflate(inflater, container, false)

        binding.theQuestion.text = viewModel.controller.items[viewModel.count_question].question
        binding.radioButton1.text = viewModel.controller.items[viewModel.count_question].answers[0]
        binding.radioButton2.text = viewModel.controller.items[viewModel.count_question].answers[1]
        binding.radioButton3.text = viewModel.controller.items[viewModel.count_question].answers[2]
        binding.radioButton4.text = viewModel.controller.items[viewModel.count_question].answers[3]
        if (viewModel.count_question == 7)
            binding.nextQuestionButton.text = "Finish Quiz"
        else
            binding.nextQuestionButton.text = "Next question"

        println(viewModel.controller.items[viewModel.count_question].question)
        println(viewModel.count_question)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val nextQuestionButton = binding.nextQuestionButton


        nextQuestionButton.setOnClickListener {
            Log.d(TAG, "Clicked on the next/finish button")
            val checkedId = binding.RadioGroup.checkedRadioButtonId

            if (checkedId == -1) {
                Toast.makeText(activity, "Answer the question!", Toast.LENGTH_LONG).show()
            } else {
                var correctAnswerId = viewModel.controller.items[viewModel.count_question].correct
                if (requireView().findViewById<RadioButton>(checkedId).text == viewModel.controller.items[viewModel.count_question].answers[correctAnswerId - 1])
                    viewModel.controller.correctAnswers++

                binding.RadioGroup.clearCheck()

                println("chechkedid" + checkedId)
                println("correctanswerid" + correctAnswerId)
                println(viewModel.controller.items[viewModel.count_question].answers[correctAnswerId - 1])
                viewModel.count_question++
                val fragmentTransaction = parentFragmentManager.beginTransaction()
                if (viewModel.count_question == 8)
                    fragmentTransaction.replace(R.id.fragment_container_view, QuizEndFragment())
                else
                    fragmentTransaction.replace(R.id.fragment_container_view, QuestionFragment())
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit()
            }

        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                alertUser()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            callback
        )
    }
    fun alertUser() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("WARNING!")
        builder.setMessage("Are you sure you want to finish the quiz? Your progress will be lost!")
        builder.setPositiveButton("Yes") { dialog, which ->
            viewModel.resetQuiz()
            val fragmentManager = parentFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.fragment_container_view, QuizStartFragment())
            fragmentManager.commit()
        }
        builder.setNegativeButton("No, I want to continue") { dialog, which ->
            dialog.cancel()
        }
        builder.show()
    }


}