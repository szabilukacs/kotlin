package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding
import com.zoltanlorinczi.project_retrofit.fragment.*
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)

        this.supportActionBar?.hide()

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Bottom Navigationhoz
        handleNavigation(myBottomNavigationView)
        // Kezeli, hogy mikor mutassa, mikor ne a BottomNavigationt
        showBottomNavigationMenuHandler(
            myBottomNavigationView,
            navController
        )

        // attol fuggoen meg ervenyes-e a token beallitja az egyik screent
        val b = intent.extras
        if (b != null) {
            val tokenIsValid = b.getBoolean("token_is_valid")
            if (tokenIsValid) {
                navController.navigate(R.id.mainScreenFragment)
            } else {
                navController.navigate(R.id.loginFragment)
            }
            App.sharedPreferences.putBooleanValue(
                SharedPreferencesManager.KEY_IS_LOGGED_IN,
                tokenIsValid
            )
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

    private fun handleNavigation(myBottomNavigationView: BottomNavigationView) {
        Log.d(TAG, "Belepett a handlenavigationba")
        myBottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activitiesFragment -> {
                    if (checkTokenIsValid())
                        loadFragment(ActivitiesFragment())
                    else
                        loadFragment(LoginFragment())
                }
                R.id.listFragment -> {
                    if (checkTokenIsValid())
                        loadFragment(TasksListFragment())
                    else
                        loadFragment(LoginFragment())
                }

                R.id.groupFragment -> {
                    if (checkTokenIsValid())
                        loadFragment(GroupFragment())
                    else
                        loadFragment(LoginFragment())
                }
                R.id.myProfileFragment -> {
                    if (checkTokenIsValid())
                        loadFragment(MyProfileFragment())
                    else
                        loadFragment(LoginFragment())
                }
                else -> {
                    Log.d("Bottom Navigation", "Error")
                    false
                }
            }
        }
    }

    private fun showBottomNavigationMenuHandler(
        myBottomNavigationView: BottomNavigationView,
        navController: NavController
    ) {
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.loginFragment || nd.id == R.id.mainScreenFragment || nd.id == R.id.forgetPasswordFragment) {
                myBottomNavigationView.visibility = View.GONE
            } else {
                myBottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    private fun checkTokenIsValid(): Boolean {
        return App.sharedPreferences.getBooleanValue(
            SharedPreferencesManager.KEY_IS_LOGGED_IN,
            false
        )
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }
}