package com.zoltanlorinczi.project_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.zoltanlorinczi.project_retrofit.api.MarketPlaceRepository

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class ListViewModelFactory(private val repository: MarketPlaceRepository) : Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(repository) as T
    }
}