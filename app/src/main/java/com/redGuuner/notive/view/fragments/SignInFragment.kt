package com.redGuuner.notive.view.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redGuuner.notive.R
import com.redGuuner.notive.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.sign_in_fragment.*

class SignInFragment : Fragment(R.layout.sign_in_fragment) {


    lateinit var viewModel: SignInViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewModel()
        setUpObservers()
    }


    override fun onResume() {
        super.onResume()
        SingInButton.setOnClickListener {
            //Do some Input validation
            viewModel.signIn(SignInEmail.text.toString(), SignInPassword.text.toString())

        }
    }


    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

    private fun setUpObservers() {
        viewModel.signInResult.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                toAddInformation()
            }
        })
        viewModel.signInException.observe(viewLifecycleOwner, Observer { exception ->
            Toast.makeText(this.context, exception, Toast.LENGTH_LONG).show()
        })

    }

    private fun toAddInformation() {
        findNavController().navigate(R.id.action_registrationFragment_to_addInformationFragment)
    }


}