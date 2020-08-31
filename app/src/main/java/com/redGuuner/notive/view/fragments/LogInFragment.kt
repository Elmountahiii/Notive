package com.redGuuner.notive.view.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redGuuner.notive.R
import com.redGuuner.notive.viewModel.LogInViewModel
import kotlinx.android.synthetic.main.log_in_fragment.*

class LogInFragment : Fragment(R.layout.log_in_fragment) {

    lateinit var viewModel: LogInViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewModel()
        setUpObservers()

    }


    override fun onResume() {
        super.onResume()
        LogInButton.setOnClickListener {
            //Do some Input validation
            viewModel.logIn(LoginEmail.text.toString(), LogInPassword.text.toString())

        }
        ForgetPasswordText.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_passwordFragment)

        }
    }


    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
    }

    private fun setUpObservers() {
        viewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->

            if (result) {

                toProfile()
            }

        })

        viewModel.loginException.observe(viewLifecycleOwner, Observer { exception ->

            Toast.makeText(this.context, exception, Toast.LENGTH_LONG).show()


        })

    }


    private fun toProfile() {

        findNavController().navigate(R.id.action_registrationFragment_to_profileFragment)

    }
    private fun toForgetPassword(){
        findNavController().navigate(R.id.action_registrationFragment_to_passwordFragment)

    }
}