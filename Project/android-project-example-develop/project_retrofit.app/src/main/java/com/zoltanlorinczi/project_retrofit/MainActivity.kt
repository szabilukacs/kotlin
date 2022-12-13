package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding
import com.zoltanlorinczi.project_retrofit.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)

        // attol fuggoen meg ervenyes-e a token beallitja az egyik screent
        val b = intent.extras
        if (b != null) {
            val token_is_valid = b.getBoolean("token_is_valid");

            Log.d(TAG, token_is_valid.toString())

            if (token_is_valid)
                setContentView(R.layout.fragment_main)
            else {
                val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)
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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        return true
    }
}