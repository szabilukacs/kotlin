package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.CreateTaskViewModelFactory
import java.lang.NumberFormatException

class CreateTaskFragment : Fragment() {

    private val TAG: String = javaClass.simpleName

    private lateinit var createTaskViewModel: CreateTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CreateTaskViewModelFactory(ThreeTrackerRepository())
        createTaskViewModel = ViewModelProvider(this, factory)[CreateTaskViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "Oncreateviev eleje")

        val view = inflater.inflate(R.layout.fragment_create_task, container, false)

        val button: Button = view.findViewById(R.id.button_create_task)
        val titleEdit: EditText = view.findViewById(R.id.edittext_title)
        val descriptionEdit: EditText = view.findViewById(R.id.editText_description)
        val assignedToEdit: EditText = view.findViewById(R.id.edittext_assign_to)
        val priorityEdit: EditText = view.findViewById(R.id.edittext_priority)
        val deadlineEdit: EditText = view.findViewById(R.id.edittext_due_date_long)
        val departmentIdEdit: EditText = view.findViewById(R.id.edittext_department_id)
        val statusEdit: RadioGroup = view.findViewById(R.id.RadioGroup)
        val progressEdit: SeekBar = view.findViewById(R.id.seekBar_progress)

        button.setOnClickListener {
            Log.d(TAG, "Clicked to create")

            val title = titleEdit.text.toString()
            val description = descriptionEdit.text.toString()
            val assignedTo = assignedToEdit.text.toString()
            val priority = priorityEdit.text.toString()
            val deadline = deadlineEdit.text.toString()
            val departmentId = departmentIdEdit.text.toString()
            val status: Int = when (statusEdit.checkedRadioButtonId) {
                R.id.radioButton_new -> {
                    0
                }
                R.id.radioButton_in_progress -> {
                    1
                }
                R.id.radioButton_done -> {
                    2
                }
                R.id.radioButton_blocked -> {
                    3
                }
                else -> {
                    -1
                }
            }
            val progress = progressEdit.progress

            // Ellenorzes
            try {
                Integer.parseInt(assignedTo)
                Integer.parseInt(priority)
                Integer.parseInt(deadline)
                Integer.parseInt(departmentId)

                if ((title == "") || (description == "") || (status == -1) || (deadline == "") || (assignedTo == "")) {
                    Toast.makeText(activity, "Fill all mandatory fileds!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Egyenlore 7 department van
                    if ((departmentId.toInt() > 7) || (departmentId.toInt() < 0))
                        Toast.makeText(activity, "Incorrect departmentId", Toast.LENGTH_SHORT)
                            .show()
                    else {
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
                        createTaskViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                            Log.d(TAG, "isSuccesful response = $it")
                            if (it == true) {
                                Toast.makeText(
                                    activity,
                                    "Task added successfully",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                // load fragment
                                val transaction = parentFragmentManager.beginTransaction()
                                transaction.replace(R.id.nav_host_fragment, TasksListFragment())
                                transaction.addToBackStack(null)
                                transaction.commit()

                            } else
                            // department id miatt egyenlore, majd azt is lecheckolni
                                Toast.makeText(
                                    activity,
                                    "Task cannot be created!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                        }
                    }
                }
            } catch (nfe: NumberFormatException) {
                Toast.makeText(activity, "Only numbers allowed!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Valami nem jol lett beirva!")
            }

        }
        Log.d(TAG, "Oncreateviev vege")

        return view
    }

}