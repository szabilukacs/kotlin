package com.example.labor_android.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.labor_android.R
import com.example.labor_android.databinding.FragmentQuizStartBinding

class QuizStartFragment : Fragment(R.layout.fragment_quiz_start) {

    lateinit var binding : FragmentQuizStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizStartBinding.inflate(inflater, container, false)
        return binding.root
    }

   private val TAG: String = javaClass.simpleName

    fun onCreateView(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val startButton = view.findViewById<Button>(R.id.start_button_quiz)

        startButton.setOnClickListener{
            Toast.makeText(getActivity(),"Clicked on the button!",Toast.LENGTH_LONG).show()
            Log.d(TAG,"Clicked on button")

        }

    }

}