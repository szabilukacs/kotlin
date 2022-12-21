package com.zoltanlorinczi.project_retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.model.GetActivitiesResponse
import com.zoltanlorinczi.project_retrofit.api.model.GetDepartmentsResponse

class ActivitiesAdapter(
    private var list: ArrayList<GetActivitiesResponse>,
    private val context: Context,
) :
    RecyclerView.Adapter<ActivitiesAdapter.SimpleDataViewHolder>() {

    open inner class SimpleDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

        override fun onLongClick(v: View?): Boolean {
            TODO("Not yet implemented")
        }
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : SimpleDataViewHolder(itemView) {
        val subIdTextView: TextView = itemView.findViewById(R.id.sub_id_textview)
        val subTypeTextView: TextView = itemView.findViewById(R.id.sub_type_textview)
        val typeTextView: TextView = itemView.findViewById(R.id.type_textview)
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleDataViewHolder {
        return when (viewType) {
            TaskListItemType.COMPLEX.value -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activities_list_item, parent, false)
                DataViewHolder(itemView)
            }
            else -> {
                throw IllegalStateException("Type is not supported!")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TaskListItemType.COMPLEX.value
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: SimpleDataViewHolder, position: Int) {
        if (getItemViewType(position) == TaskListItemType.COMPLEX.value) {
            val complexHolder = (holder as DataViewHolder)
            val currentItem = list[position]
            complexHolder.typeTextView.text = currentItem.type.toString()
            complexHolder.subTypeTextView.text = currentItem.sub_type.toString()
            complexHolder.subIdTextView.text = currentItem.sub_ID.toString()
        }
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<GetActivitiesResponse>) {
        list = newList
    }

    private enum class TaskListItemType(val value: Int) {
        COMPLEX(1)
    }
}