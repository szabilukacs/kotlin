package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GetDepartmentsResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class GetDepartmentsViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<GetDepartmentsResponse>> = MutableLiveData()

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
                    repository.getDepartments(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "GetDepartments response: ${response.body()}")

                    val departmentsList = response.body()
                    departmentsList?.let {
                        products.value = departmentsList
                    }
                } else {
                    Log.d(TAG, "GetDepartments error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(
                    TAG,
                    "GetDepartmentsViewModel - getGetDepartments() failed with exception: ${e.message}"
                )
            }
        }
    }

}