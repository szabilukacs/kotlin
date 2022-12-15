package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zoltanlorinczi.project_retorfit.R

class ActivitiesFragment : Fragment() {

    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ActivitiesFragment","Letrejott")
        // ellenorizni a tokent
        // lekerni az activitiket, ha rossz response akkor error oldalt betolteni

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activities, container, false)

        return view
    }

}