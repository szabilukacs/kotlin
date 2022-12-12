package com.zoltanlorinczi.project_retrofit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.zoltanlorinczi.project_retorfit.R
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.fragment.LoginFragment
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.LoginViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.LoginViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "Splash onCreate() called!")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val factory = LoginViewModelFactory(ThreeTrackerRepository())
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        //TODO: Ide a tokent megnezni valid-e, aszerint login vagy main screen
        // lekerem a sajat useremet, aszerint donti el hova dob be




        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // HERE WE ARE TAKING THE REFERENCE OF OUR IMAGE
        // SO THAT WE CAN PERFORM ANIMATION USING THAT IMAGE
        val backgroundImage: ImageView = findViewById(R.id.SplashScreenImage)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        backgroundImage.startAnimation(slideAnimation)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }

    fun checkToken():Boolean{

        val token = App.sharedPreferences.getStringValue(
            SharedPreferencesManager.KEY_TOKEN,
            "Empty token!"
        )
        if (token == "Empty token!")
            return false

        // get my user

        return true
    }
    private fun getMyUser() {

        // megcsinalni kulon viewmodellel s factoryval a getmyusert is
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getTasks(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TasksViewModel.TAG, "Get tasks response: ${response.body()}")

                    val tasksList = response.body()
                    tasksList?.let {
                        products.value = tasksList
                    }
                } else {
                    Log.d(TasksViewModel.TAG, "Get tasks error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TasksViewModel.TAG, "TasksViewModel - getTasks() failed with exception: ${e.message}")
            }

    }
}
