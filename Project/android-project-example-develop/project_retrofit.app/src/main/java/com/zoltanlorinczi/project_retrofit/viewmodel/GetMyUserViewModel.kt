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
        // itt majd visszateriteni a tobbi adatot is
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
                } else {
                    Log.d(TAG, "GetMyUser error response: ${response?.errorBody()}")
                    token_splashez = false;
                }

            } catch (e: Exception) {
                Log.d(TAG, "GetMyUserViewModel - getMyUser() failed with exception: ${e.message}")
            }
        }
    }

}