package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import com.zoltanlorinczi.project_retrofit.viewmodel.UpdateProfileViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.UpdateProfileViewvModelFactory

class EditProfileFragment : Fragment() {

    private val TAG: String = javaClass.simpleName

    private lateinit var updateProfileViewModel: UpdateProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = UpdateProfileViewvModelFactory(ThreeTrackerRepository())
        updateProfileViewModel =
            ViewModelProvider(this, factory)[UpdateProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        val button: Button = view.findViewById(R.id.button_save_edit)
        val lastNamEdit: EditText = view.findViewById(R.id.edittext_last_name)
        val firstnameEdit: EditText = view.findViewById(R.id.edittext_first_name)
        val locationEdit: EditText = view.findViewById(R.id.edittext_location)
        val phoneNumberEdit: EditText = view.findViewById(R.id.edittext_phone_number)
        val imageUrlEdit: EditText = view.findViewById(R.id.edittext_imageUrl)

        lastNamEdit.hint =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_LAST_NAME, "null")
        firstnameEdit.hint =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_FIRST_NAME, "null")
        locationEdit.hint =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_LOCATION, "null")
        phoneNumberEdit.hint =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_PHONE_NUMBER, "null")
        imageUrlEdit.hint =
            App.sharedPreferences.getStringValue(SharedPreferencesManager.KEY_IMAGE, "null")

        button.setOnClickListener {
            Log.d(TAG, "Clicked to update")

            val lastName = lastNamEdit.text.toString()
            val firstName = firstnameEdit.text.toString()
            val location = locationEdit.text.toString()
            val phoneNumber = phoneNumberEdit.text.toString()
            val imageUrl = imageUrlEdit.text.toString()

            updateProfileViewModel.updateProfile(
                lastName,
                firstName,
                location,
                phoneNumber,
                imageUrl
            )

            updateProfileViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "isSuccesful response = $it")
                if (it == true) {
                    Toast.makeText(
                        activity,
                        "Profile Updated successfully",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    // load fragment
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, MyProfileFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()

                } else
                    Toast.makeText(
                        activity,
                        "Profile cannot be updated!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
            }
        }
        return view
    }
}