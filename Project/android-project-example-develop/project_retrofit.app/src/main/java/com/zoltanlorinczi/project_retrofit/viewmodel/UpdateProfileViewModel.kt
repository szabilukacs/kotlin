package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.CreateTaskRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.updateProfileRequestBody
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProfileViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun updateProfile(
        lastName: String,
        firstName: String,
        location: String,
        phoneNumber: String,
        imageUrl: String,
    ) {
        val requestBody = updateProfileRequestBody(
            lastName, firstName, location, phoneNumber, imageUrl
        )
        viewModelScope.launch {
            executeUpdateProfile(requestBody)
        }
    }

    private suspend fun executeUpdateProfile(requestBody: updateProfileRequestBody) {
        try {
            val token: String? = App.sharedPreferences.getStringValue(
                SharedPreferencesManager.KEY_TOKEN,
                "Empty token!"
            )
            if (token != null) {
                val response = withContext(Dispatchers.IO) {
                    repository.updateProfile(token, requestBody)
                }

                if (response.isSuccessful) {
                    Log.d(TAG, "Update Profile response: ${response.body()}")
                    isSuccessful.value = true
                } else {
                    Log.d(TAG, "Update Profile error response: ${response.message()}")
                    isSuccessful.value = false
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Update Profile failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }

}