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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.fragment.LoginFragment
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    private lateinit var getMyUserViewModel: GetMyUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "Splash onCreate() called!")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val factory = GetMyUserViewModelFactory(ThreeTrackerRepository())
        getMyUserViewModel = ViewModelProvider(this, factory)[GetMyUserViewModel::class.java]

        Log.d(TAG,
            "Splash - token = " + App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
        )

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // image + animation
        val backgroundImage: ImageView = findViewById(R.id.SplashScreenImage)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        backgroundImage.startAnimation(slideAnimation)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            val b = Bundle()
            b.putBoolean("token_is_valid", getMyUserViewModel.token_splashez); //jo e vagy nem
            intent.putExtras(b)
            startActivity(intent)
            finish()
        }, 3000)
    }


//    fun checkToken():Boolean{
//
//        val token = App.sharedPreferences.getStringValue(
//            SharedPreferencesManager.KEY_TOKEN,
//            "Empty token!"
//        )
//        if (token == "Empty token!")
//            return false
//        Log.d(TAG, "Itt vagyunk")
//       // Log.d(TAG, getMyUserViewModel.token.toString())
//
//        // get my user
//
//        return true
//    }

}
