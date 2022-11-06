package com.example.labor_android.fragment

import ItemController
import ItemRepository
import ItemService
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var count_question = 0 // 9 kerdes van -> max 8
    val controller = ItemController(ItemService(ItemRepository))
    //val itemsproba = controller.quiz(9)
}
