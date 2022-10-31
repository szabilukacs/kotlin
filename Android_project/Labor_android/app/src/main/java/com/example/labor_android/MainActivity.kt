package com.example.labor_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"app started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById(R.id.start_button_quiz)
        startButton.setOnClickListener{
            Log.d(TAG,"Clicked on button")
            Toast.makeText(applicationContext,"Clicked on the button!",Toast.LENGTH_LONG).show()
            Log.d(TAG,"Clicked on button")
            
            // manifestbe kijavitani
            val intent = Intent(this, SecondActivity::class.java)
            val value : EditText = findViewById(R.id.plain_text_input)
            // To pass any data to next activity
            intent.putExtra("name_input", value.text.toString())
            // start your next activity
            Log.d(TAG,"Start new Activity")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}