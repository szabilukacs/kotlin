package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.TasksListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/2/2021
 */
class TasksListFragment : Fragment(R.layout.fragment_tasks_list),
    TasksListAdapter.OnItemClickListener,
    TasksListAdapter.OnItemLongClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TasksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tasks_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        tasksViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "Tasks list = $it")
            adapter.setData(tasksViewModel.products.value as ArrayList<TaskResponse>)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupRecyclerView() {
        adapter = TasksListAdapter(ArrayList(), requireContext(), this, this)
        Log.d(TAG, adapter.toString())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.itemAnimator = SlideInUpAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        Log.d(TAG, "Clicked on item, Pozition: ")
        Log.d(TAG, position.toString())

        val b = Bundle()
        b.putInt("position", position)
        val taskDetail = tasksViewModel.products.value?.get(position)
        if (taskDetail!= null)
        {
            b.putInt("id",taskDetail.id)
            b.putString("title",taskDetail.title)
            b.putString("description",taskDetail.description)
            b.putLong("createdTime",taskDetail.createdTime)
            b.putInt("createdByUserID",taskDetail.createdByUserID)
            b.putInt("assignedToUserID",taskDetail.assignedToUserID)
            b.putInt("priority",taskDetail.priority)
            b.putLong("deadline",taskDetail.deadline)
            b.putInt("departmentId",taskDetail.departmentID)
            b.putString("status", taskDetail.status.toString())
            b.putString("progress",taskDetail.progress)
        }

        val taskDetailFragment = TaskDetailFragment()
        taskDetailFragment.arguments = b
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, taskDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }
}