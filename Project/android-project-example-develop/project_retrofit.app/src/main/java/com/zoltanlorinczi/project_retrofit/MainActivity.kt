package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // attol fuggoen meg ervenyes-e a token beallitja az egyik screent
        val b = intent.extras
        if (b != null) {
            val token_is_valid = b.getBoolean("token_is_valid");

            Log.d(TAG, token_is_valid.toString())

            if (token_is_valid) {
                navController.navigate(com.zoltanlorinczi.project_retorfit.R.id.mainScreenFragment)
            } else {
                navController.navigate(com.zoltanlorinczi.project_retorfit.R.id.loginFragment)
            }
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

    private fun loadFragment(fragment: Fragment): Boolean {
        // val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(R.id.nav_host_fragment, fragment)
        // transaction.addToBackStack(null)
        // transaction.commit()
        //Navigation.findNavController(R.id.nav_host_fragment)
        return true
    }
}