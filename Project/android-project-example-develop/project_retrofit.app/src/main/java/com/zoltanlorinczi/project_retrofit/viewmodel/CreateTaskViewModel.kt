package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.CreateTaskRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.ForgetPasswordRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.LoginRequestBody
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CreateTaskViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var token: MutableLiveData<String> = MutableLiveData()
    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun createTask(
        title: String,
        description: String,
        assignedToUserId: Int,
        priority: Int,
        deadline: Long,
        departmentId: Int,
        status: String,
        progress: Int,
    ) {
        val requestBody = CreateTaskRequestBody(
            title,
            description,
            // kesobb betenni mai datumra, nem longra
            createdTime = 1234567,
            assignedToUserId,
            priority,
            deadline,
            departmentId,
            status,
            progress
        )
        viewModelScope.launch {
            executeCreateTask(requestBody)
        }
    }

    private suspend fun executeCreateTask(requestBody: CreateTaskRequestBody) {
        try {
            val response = withContext(Dispatchers.IO) {
                repository.createTask(requestBody)
            }

            if (response.isSuccessful) {
                Log.d(TAG, "Create task response: ${response.body()}")
            } else {
                Log.d(TAG, "Create task error response: ${response.message()}")
                isSuccessful.value = false
            }
        } catch (e: Exception) {
            Log.d(TAG, "Create task failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }

}