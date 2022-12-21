package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GetActivitiesRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.GetActivitiesResponse
import com.zoltanlorinczi.project_retrofit.api.model.GetDepartmentsResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class ActivitiesViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<GetActivitiesResponse>> = MutableLiveData()

    fun getActivities() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )

                val response = token?.let {
                    repository.getActivities(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "GetActivities response: ${response.body()}")

                    val activitiesList = response.body()
                    activitiesList?.let {
                        products.value = activitiesList
                    }
                } else {
                    Log.d(TAG, "GetActivities error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(
                    TAG,
                    "GetActivities - getActivities() failed with exception: ${e.message}"
                )
            }
        }
    }
}