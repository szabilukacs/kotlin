package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GetMyUserResponse
import com.zoltanlorinczi.project_retrofit.api.model.LoginRequestBody
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class GetMyUserViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var token_splashez: Boolean = false

    init {
        getMyUser()
    }

    fun getMyUser() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )

                val response = token?.let {
                    repository.getMyUser(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "GetMyUser response: ${response.body()}")
                    token_splashez = true;

                    // kulon fgv-be tenni majd
                    val ID = response.body()?.id
                    val last_name = response.body()?.last_name
                    val first_name = response.body()?.first_name
                    val email= response.body()?.email
                    val type = response.body()?.type
                    val location = response.body()?.location
                    val phone_number = response.body()?.phone_number
                    val department_id = response.body()?.department_id
                    val image = response.body()?.image

                    ID?.let {
                        App.sharedPreferences.putIntValue(
                            SharedPreferencesManager.KEY_ID,
                            it
                        )
                    }
                    last_name?.let {
                        App.sharedPreferences.putStringValue(
                            SharedPreferencesManager.KEY_LAST_NAME,
                            it
                        )
                    }
                    first_name?.let {
                        App.sharedPreferences.putStringValue(
                            SharedPreferencesManager.KEY_FIRST_NAME,
                            it
                        )
                    }
                    email?.let {
                        App.sharedPreferences.putStringValue(
                            SharedPreferencesManager.KEY_EMAIL,
                            it
                        )
                    }
                    type?.let {
                        App.sharedPreferences.putIntValue(
                            SharedPreferencesManager.KEY_TYPE,
                            it
                        )
                    }
                    location?.let {
                        App.sharedPreferences.putStringValue(
                            SharedPreferencesManager.KEY_LOCATION,
                            it
                        )
                    }
                    phone_number?.let {
                        App.sharedPreferences.putStringValue(
                            SharedPreferencesManager.KEY_PHONE_NUMBER,
                            it
                        )
                    }
                    department_id?.let {
                        App.sharedPreferences.putIntValue(
                            SharedPreferencesManager.KEY_DEPARTMENT_ID,
                            it
                        )
                    }
                    image?.let {
                        App.sharedPreferences.putStringValue(
                            SharedPreferencesManager.KEY_IMAGE,
                            it
                        )
                    }




                } else {
                    Log.d(TAG, "GetMyUser error response: ${response?.errorBody()}")
                    token_splashez = false;
                }

            } catch (e: Exception) {
                Log.d(TAG, "GetMyUserViewModel - getMyUser() failed with exception: ${e.message}")
            }
        }
    }

    private fun saveUserData()
    {

    }

}