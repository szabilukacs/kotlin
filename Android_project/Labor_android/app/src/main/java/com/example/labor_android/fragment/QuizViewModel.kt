package com.example.labor_android.fragment

import ItemController
import ItemRepository
import ItemService
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    val num_of_questions = 9
    var count_question = 0 // 9 kerdes van -> max 8
    val controller = ItemController(ItemService(ItemRepository))

    fun resetQuiz()
    {
        controller.correctAnswers = 0
        count_question = 0
    }
}
