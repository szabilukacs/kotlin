package com.example.labor_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"app started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById(R.id.start_button_quiz)
        startButton.setOnClickListener{
            Toast.makeText(applicationContext,"Clicked on the button!",Toast.LENGTH_LONG).show()
            Log.d(TAG,"Clicked on button")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}