package com.zoltanlorinczi.project_retrofit.fragment

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMainScreenBinding
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMyProfileBinding
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.MainActivity
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

class MyProfileFragment : Fragment() {

    private val TAG: String = javaClass.simpleName

    private lateinit var getMyUserViewModel: GetMyUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GetMyUserViewModelFactory(ThreeTrackerRepository())
        getMyUserViewModel = ViewModelProvider(this, factory)[GetMyUserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //
        // Button a log outhoz meg
        // ott torolni a tokent, majd attenni a log inhez

        val binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        val ID = App.sharedPreferences.getIntValue(SharedPreferencesManager.KEY_ID, 0)
        val last_name =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_LAST_NAME, "null")
        val first_name =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_FIRST_NAME, "null")
        val email = App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_EMAIL, "null")
        val type = App.sharedPreferences.getIntValue(SharedPreferencesManager.KEY_TYPE, 0)
        val location =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_LOCATION, "null")
        val phone_number =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_PHONE_NUMBER, "null")
        val department_id =
            App.sharedPreferences.getIntValue(SharedPreferencesManager.KEY_DEPARTMENT_ID, 0)
        val image = App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_IMAGE, "null")

        val button = binding.buttonLogOut

        binding.firstName.text = first_name
        binding.lastName.text = last_name
        binding.email.text = email
        binding.location.text = location
        binding.type.text = type.toString()
        binding.phoneNumber.text = phone_number

        button.setOnClickListener {
            Log.d(TAG, "Clicked on Log out")
            // delete token
            App.sharedPreferences.putStringValue(SharedPreferencesManager.KEY_TOKEN, "")
            App.sharedPreferences.putBooleanValue(SharedPreferencesManager.KEY_IS_LOGGED_IN, false)

//            ezzel nem megy
//            findNavController().navigate(R.id.action_myProfileFragment_to_loginFragment)

            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, LoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()


        }

        // image with Glide
        context?.let { Glide.with(it).load(image).into(binding.profileImage) }

        return binding.root
    }

}
