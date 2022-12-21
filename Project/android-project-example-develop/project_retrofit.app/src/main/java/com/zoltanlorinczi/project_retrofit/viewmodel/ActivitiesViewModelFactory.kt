package com.zoltanlorinczi.project_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository

class ActivitiesViewModelFactory(private val repository: ThreeTrackerRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivitiesViewModel(repository) as T
    }
}