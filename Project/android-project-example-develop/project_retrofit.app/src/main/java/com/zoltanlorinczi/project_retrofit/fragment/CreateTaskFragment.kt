package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentCreateTaskBinding
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModelFactory
import java.lang.Error

class CreateTaskFragment : Fragment() {

    private val TAG: String = javaClass.simpleName

    private lateinit var createTaskViewModel: CreateTaskViewModel
    lateinit var binding: FragmentCreateTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CreateTaskViewModelFactory(ThreeTrackerRepository())
        createTaskViewModel = ViewModelProvider(this, factory)[CreateTaskViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG,"Oncreateviev eleje")

        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        // itt lesz egy edit page
        // szovegmezok ahol meglehet adni title stb.
        // ide majd betenni a create task viewot a taskdetailbol
        val button = binding.buttonCreateTask

        val title = binding.edittextTitle.text.toString()
        val description = binding.editTextDescription.text.toString()
        val assignedTo = binding.edittextAssignTo.text.toString()
        val priority = binding.edittextPriority.text.toString()
        val deadline = binding.edittextDueDateLong.toString()
        val departmentId = binding.edittextDepartmentId.text.toString()


        val status = binding.RadioGroup.checkedRadioButtonId.toString()
        val progress = binding.seekBarProgress.progress
        Log.d(TAG, status)

        Log.d(TAG,"Oncreateviev vege")

        button.setOnClickListener {
            Log.d(TAG,"Clicked to create")
            createTaskViewModel.createTask(
                title,
                description,
                assignedTo.toInt(),
                priority.toInt(),
                deadline.toLong(),
                departmentId.toInt(),
                status,
                progress
            )
            Log.d(TAG,"Tuljutott ezen")
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

}