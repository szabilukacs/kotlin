package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private val TAG: String = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "hellocska")

        val button1: Button = binding.buttonActivities
        val button2: Button = binding.buttonMyTasks
        val button3: Button = binding.buttonGroups
        val button4: Button = binding.buttonProfile

        Log.d(TAG, button1.text.toString())

        button1.setOnClickListener {
            Log.d(TAG, "hello")
            findNavController().navigate(R.id.activitiesFragment)
        }
        button2.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }
        button3.setOnClickListener {
            findNavController().navigate(R.id.groupFragment)
        }
        button4.setOnClickListener {
            findNavController().navigate(R.id.myProfileFragment)
        }
    }

}