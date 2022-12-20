package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.FragmentMainScreenBinding
import com.zoltanlorinczi.project_retrofit.adapter.DepartmentListAdapter
import com.zoltanlorinczi.project_retrofit.adapter.TasksListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GetDepartmentsResponse
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.*
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class GroupFragment : Fragment(R.layout.fragment_group) {

    // Groupnal csak a csoportok mennek a BE-nel, eleg az most
    // Most groupnal ez
    // Sima profile update
    // Activitiesnel is csak sima lekeres

    private val TAG: String = javaClass.simpleName

    private lateinit var departmentsViewModel: GetDepartmentsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DepartmentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GetDepartmentsViewModelFactory(ThreeTrackerRepository())
        departmentsViewModel = ViewModelProvider(this, factory)[GetDepartmentsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_group)
        setupRecyclerView()

        departmentsViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "Departments list = $it")
            adapter.setData(departmentsViewModel.products.value as ArrayList<GetDepartmentsResponse>)
            adapter.notifyDataSetChanged()
        }
        
        return view
    }

    private fun setupRecyclerView() {
        adapter = DepartmentListAdapter(ArrayList(), requireContext())
        Log.d(TAG, adapter.toString())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.setHasFixedSize(true)
    }

}