package com.zoltanlorinczi.project_retrofit.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/6/2021
 */
class TasksListAdapter(
        private var list: ArrayList<TaskResponse>,
        private val context: Context,
        private val listener: OnItemClickListener,
        private val listener2: OnItemLongClickListener
) :
        RecyclerView.Adapter<TasksListAdapter.DataViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener, View.OnLongClickListener {
        val taskTitleTextView: TextView = itemView.findViewById(R.id.task_title_view)
        val taskDescriptionTextView: TextView = itemView.findViewById(R.id.task_description_view)
        val taskPriorityTextView: TextView = itemView.findViewById(R.id.task_priority_view)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)

        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tasks_list_item, parent, false)
        return DataViewHolder(itemView)
    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]

        holder.taskTitleTextView.text = currentItem.title
        holder.taskDescriptionTextView.text = currentItem.description

        if (currentItem.priority == 0) {
            holder.taskPriorityTextView.setBackgroundColor(Color.RED)
        } else if (currentItem.priority == 1) {
            holder.taskPriorityTextView.setBackgroundColor(Color.YELLOW)
        } else if (currentItem.priority == 2) {
            holder.taskPriorityTextView.setBackgroundColor(Color.GREEN)
        }

//        Glide.with(this.context)
//            .load(R.drawable.ic_launcher_background)
//            .override(200, 200)
//            .into(holder.imageView);
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newList: ArrayList<TaskResponse>) {
        list = newList
    }
}