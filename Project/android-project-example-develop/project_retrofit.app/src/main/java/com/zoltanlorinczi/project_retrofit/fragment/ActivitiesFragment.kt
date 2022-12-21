package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.ActivitiesAdapter
import com.zoltanlorinczi.project_retrofit.adapter.DepartmentListAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GetActivitiesRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.GetActivitiesResponse
import com.zoltanlorinczi.project_retrofit.api.model.GetDepartmentsResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.ActivitiesViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ActivitiesViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.GetDepartmentsViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.GetDepartmentsViewModelFactory

class ActivitiesFragment : Fragment() {

    private val TAG: String = javaClass.simpleName

    private lateinit var activiesViewModel: ActivitiesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActivitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ActivitiesViewModelFactory(ThreeTrackerRepository())
        activiesViewModel = ViewModelProvider(this, factory)[ActivitiesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activities, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_activities)
        setupRecyclerView()

        activiesViewModel.getActivities()

        activiesViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "Activities list = $it")
            adapter.setData(activiesViewModel.products.value as ArrayList<GetActivitiesResponse>)
            adapter.notifyDataSetChanged()
        }

        return view
    }

    private fun setupRecyclerView() {
        adapter = ActivitiesAdapter(ArrayList(), requireContext())
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