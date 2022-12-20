package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.databinding.FragmentTaskDetailBinding
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory
import kotlin.math.log

class TaskDetailFragment : Fragment() {

    private lateinit var createTaskViewModel: CreateTaskViewModel
    lateinit var binding: FragmentTaskDetailBinding

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CreateTaskViewModelFactory(ThreeTrackerRepository())
        createTaskViewModel = ViewModelProvider(this, factory)[CreateTaskViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        val b = this.arguments

        val button = binding.floatingActionButtonAddNewTask

        if (b != null) {
            //val taskDetail = TaskResponse()
            val position = b.getInt("position")
            val id = b.getInt("id")
            val title = b.getString("title")
            val description = b.getString("description")
            val createdTime = b.getLong("createdTime")
            val createdByUserID = b.getInt("createdByUserID")
            val assignedToUserID = b.getInt("assignedToUserID")
            val priority = b.getInt("priority")
            val deadline = b.getLong("deadline")
            val departmentId = b.getInt("departmentId")
            val status = b.getInt("status")
            val progress = b.getString("progress")

            binding.taskDetailTitle.text = title
            binding.taskDetailDescription.text = description
            binding.taskDetailAssignedTo.text = assignedToUserID.toString()
            binding.taskDetailCreatedAt.text = createdTime.toString()
            binding.taskDetailCreatedBy.text = createdByUserID.toString()
            binding.taskDetailDepartmentId.text = departmentId.toString()
            binding.taskDetailPriority.text = priority.toString()
            binding.taskDetailStatus.text = status.toString()
            binding.taskDetailProgress.text = progress
            binding.taskDetailDeadline.text = deadline.toString()

            button.setOnClickListener {
                // load create new task fragment
                // ide csak egy gomb ami atvisz a createTask fragmentre
            }
        }




        return binding.root
    }
}