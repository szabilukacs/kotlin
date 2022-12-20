package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ForgetPasswordRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.LoginRequestBody
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForgetPassViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    var inserted: Int? = 0

    fun resetPassword(email: String) {
        val requestBody = ForgetPasswordRequestBody(email)
        viewModelScope.launch {
            executeResetPassword(requestBody)
        }
    }

    private suspend fun executeResetPassword(requestBody: ForgetPasswordRequestBody) {
        try {
            val response = withContext(Dispatchers.IO) {
                repository.forgetPassword(requestBody)
            }
            if (response.isSuccessful) {
                Log.d(TAG, "Reset password response: ${response.body()}")
                inserted = response.body()?.inserted

            } else {
                Log.d(TAG, "Reset password error response: ${response.message()}")
                isSuccessful.value = false
            }
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - login() failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }

}