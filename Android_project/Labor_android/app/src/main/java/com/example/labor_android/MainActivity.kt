package com.example.labor_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.labor_android.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "app started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleNavigation()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    private  fun loadFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
        }

    private fun handleNavigation() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> loadFragment(HomeFragment())
                R.id.quizStartFragment -> loadFragment(QuizStartFragment())
                R.id.profileFragment -> loadFragment(ProfileFragment())
                R.id.questioListFragment -> loadFragment(QuestionListFragment())
                R.id.questionAddFragment -> loadFragment(QuestionAddFragment())
                else ->false
            }

        }
        //println(R.id.menu1)
        //println(item.itemId)
    }

}