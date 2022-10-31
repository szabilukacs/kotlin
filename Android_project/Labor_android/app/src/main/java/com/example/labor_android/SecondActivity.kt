package com.example.labor_android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    val player_name: String = intent.getStringExtra("name_input").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val player_name_text : TextView = findViewById(R.id.player_name)
        player_name_text.text = player_name
    }
}