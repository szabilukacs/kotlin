package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {

    lateinit var binding: FragmentMainScreenBinding
    private val TAG: String = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)

        val button1: Button = binding.buttonActivities
        val button2: Button = binding.buttonMyTasks
        val button3: Button = binding.buttonGroups
        val button4: Button = binding.buttonProfile

        button1.setOnClickListener {
            Log.d(TAG, "Click on activities")
            navBar.selectedItemId = R.id.activitiesFragment
            findNavController().navigate(R.id.activitiesFragment)
        }
        button2.setOnClickListener {
            navBar.selectedItemId = R.id.listFragment
            findNavController().navigate(R.id.listFragment)
        }
        button3.setOnClickListener {
            Log.d(TAG, "Click on group")
            navBar.selectedItemId = R.id.groupFragment
            findNavController().navigate(R.id.groupFragment)
        }
        button4.setOnClickListener {
            navBar.selectedItemId = R.id.myProfileFragment
            findNavController().navigate(R.id.myProfileFragment)
        }
        return binding.root
    }
}