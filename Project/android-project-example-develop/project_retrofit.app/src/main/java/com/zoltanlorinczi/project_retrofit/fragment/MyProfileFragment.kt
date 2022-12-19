package com.zoltanlorinczi.project_retrofit.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMainScreenBinding
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMyProfileBinding
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
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
        // profilkepet megcsinalni letoltse es mutassa
        // Betenni kepkent egy sima usert, ha null az image

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

        binding.firstName.text = first_name
        binding.lastName.text = last_name
        binding.email.text = email
        binding.location.text = location
        binding.type.text = type.toString()
        binding.phoneNumber.text = phone_number
        binding.departmentId.text = department_id.toString()


        // image
        // Declaring and initializing the elements from the layout file
        val mImageView = binding.profileImage
        // Declaring a Bitmap local
        var mImage: Bitmap?

        // Declaring a webpath as a string
        val mWebPath = image.toString()
        // Declaring and initializing an Executor and a Handler
        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())

        myExecutor.execute {
            mImage = mLoad(mWebPath)
            myHandler.post {
                mImageView.setImageBitmap(mImage)
            }
        }

        return binding.root
    }

    // Function to establish connection and load image
    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    // Function to convert string to URL
    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }

}







