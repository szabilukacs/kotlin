package com.example.labor_android.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.labor_android.R
import com.example.labor_android.databinding.FragmentHomeBinding
import com.example.labor_android.databinding.FragmentQuizStartBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val TAG: String = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val startButton = binding.testSkillsButton
        startButton.setOnClickListener {
            Log.d(TAG, "Clicked on button")
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container_view, QuizStartFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit()
        }
//        val readButton = binding.readQuestionsButton
//        readButton.setOnClickListener {
//            Log.d(TAG, "Clicked on button")
//            val fragmentTransaction = parentFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_container_view, QuizStartFragment());
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit()
//        }
//        val createButton = binding.createQuestionButton
//        createButton.setOnClickListener {
//            Log.d(TAG, "Clicked on button")
//            val fragmentTransaction = parentFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_container_view, QuizStartFragment());
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit()
//        }
    }

}