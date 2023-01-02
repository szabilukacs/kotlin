package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.ForgetPassViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ForgetPassViewModelFactory

class ForgetPasswordFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var forgetPassViewModel: ForgetPassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ForgetPassViewModelFactory(ThreeTrackerRepository())
        forgetPassViewModel = ViewModelProvider(this, factory)[ForgetPassViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password, container, false)

        val button: Button = view.findViewById(R.id.button_forgot_password_fragment)
        val emailEditText: EditText = view.findViewById(R.id.edittext_forgot_password_fragment)

        button.setOnClickListener {
            val email = emailEditText.text.toString()

            forgetPassViewModel.resetPassword(email)

            forgetPassViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d(TAG, "Password send to the email successfully = $it")
                if (it) {
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        }

        return view
    }
}