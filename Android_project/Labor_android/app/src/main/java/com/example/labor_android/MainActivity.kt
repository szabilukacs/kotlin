package com.example.labor_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.labor_android.fragment.QuizStartFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "app started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // when (handleNavigation())
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun handleNavigation() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu1 -> true
                R.id.menu2 -> true
                R.id.menu3 -> true
                R.id.menu4 -> true
                R.id.menu5 -> true
                else ->false
            }
        }
    }

}