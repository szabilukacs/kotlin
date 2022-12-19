package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding
import com.zoltanlorinczi.project_retrofit.fragment.*

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleNavigation()  // Bottom Navigationhoz

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // attol fuggoen meg ervenyes-e a token beallitja az egyik screent
        if (checkTokenIsValid()) {
            // myBottomNavigationView.menu.findItem(R.id.bottom_navigation).isEnabled = false
            navController.navigate(R.id.mainScreenFragment)
        } else {
            navController.navigate(R.id.loginFragment)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called!")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called!")
    }

    private fun checkTokenIsValid(): Boolean {
        val b = intent.extras
        if (b != null) {
            val tokenIsValid = b.getBoolean("token_is_valid");
            return tokenIsValid
        }
        // sharedpreferenciesbe betenni hogy validan be van e jelentkezve
        return false
    }

    private fun handleNavigation() {
        Log.d(TAG, "Belepett a handlenavigationba")
        val myBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        myBottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activitiesFragment -> {
                    Log.d(TAG, "Kattintott")
                    if (checkTokenIsValid())
                    {
                        Log.d(TAG,"Act fragment")
                        loadFragment(ActivitiesFragment())
                    }

                    else
                    {
                        Log.d(TAG,"Login fragment")
                        loadFragment(LoginFragment())
                    }

                }
                R.id.listFragment -> loadFragment(TasksListFragment())
                R.id.groupFragment -> loadFragment(GroupFragment())
                R.id.myProfileFragment -> loadFragment(MyProfileFragment())
                else -> {
                    Log.i("NavBar", "Error")
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }
}